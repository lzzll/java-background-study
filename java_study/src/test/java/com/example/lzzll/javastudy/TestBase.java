package com.example.lzzll.javastudy;

import com.example.lzzll.javastudy.common.util.AliyunOSSUtil;
//import com.example.lzzll.javastudy.common.util.CommonRestTemplateUtil;
import net.arnx.wmf2svg.gdi.svg.SvgGdi;
import net.arnx.wmf2svg.gdi.wmf.WmfParser;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.xml.sax.InputSource;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavastudyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebAppConfiguration
public class TestBase {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final Logger log = LoggerFactory.getLogger(TestBase.class);
    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;
    long start = 0;

    @Before
    public void init() {
//        System.out.println("开始测试:"+dateFormat.format(new Date()));
        this.start = System.currentTimeMillis();
    }

    @After
    public void after() {
//        System.out.println("结束测试:"+dateFormat.format(new Date()));
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println("耗时：" + cost);
        if (cost > 1000) {
            System.out.println("太慢了");
        }
    }

    public String readDoc(String path) {
        long start = System.currentTimeMillis();
        StringBuffer text = new StringBuffer("");
        XWPFDocument word = null;
//        HWPFDocument word = null;
        try {
//            POIFSFileSystem pfs = new POIFSFileSystem(new FileInputStream(path));
//            word = new HWPFDocument(pfs);
            word = new XWPFDocument(new FileInputStream(path));

//            WordToFoConverter converter = new WordToFoConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
//            //对HWPFDocument进行转换
//            converter.processDocument(word);
//
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer t = tf.newTransformer();
//            t.setOutputProperty("encoding", "UTF-8");// 解决中文问题，试过用GBK不行
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            t.transform(new DOMSource(converter.getDocument()), new StreamResult(bos));
//            String xml = bos.toString();

            String xml = word.getDocument().xmlText();
            String html = getXmlContent(xml, word);
            html = html.replaceAll("\\\\right", "")
                    .replaceAll("\\\\left", "");
            text.append(handleImg(html, 0));
            word.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return text.toString();
    }

    private String getXmlContent(String xml, XWPFDocument word) throws Exception {
        SAXReader reader = new SAXReader(new DocumentFactory());
        InputSource source = new InputSource(new StringReader(xml));
        source.setEncoding("utf-8");
        Document doc = reader.read(source);
        Element root = doc.getRootElement();
        return handelXml(root, word);
    }

    private String handelXml(Element element, XWPFDocument word) throws Exception {
        StringBuffer str = new StringBuffer("");
        List<Element> elementList = element.elements();
        for (Element e : elementList) {
            if (e.getQName().getQualifiedName().equals("w:p")) {
                str.append("<p>");
                str.append(handelXml(e, word));
                str.append("</p>");
            } else if (e.getQName().getQualifiedName().equals("w:tbl")) {
                str.append(handleTable(e, word));
            } else {
                if (element.getQName().getQualifiedName().equals("m:oMath") || element.getQName().getQualifiedName().equals("x:oMath")) {
                    String xml = convertOMML2MML(e.asXML());
                    str.append(convertMML2Latex(xml));
                } else if (e.selectSingleNode("w:t") != null) {
                    str.append(e.selectSingleNode("w:t").getText());
                } else if (e.selectSingleNode("w:object") != null) {//处理文件标签中的图片
                    str.append(handelXml(e, word));
                }else if(e.selectSingleNode("v:shape")!=null){
                    str.append(handelXml(e, word));
                }else if(e.selectSingleNode("v:imagedata")!=null||e.selectSingleNode("o:OLEObject")!=null){
                    if(e.selectSingleNode("v:imagedata")!=null) {
                        Element imgElement = (Element) e.selectSingleNode("v:imagedata");
                        PackagePart part = word.getPartById(imgElement.attribute(imgElement.getQName("r:id")).getValue());
                        String name = part.getPartName().getName();
                        if(name.substring(name.lastIndexOf(".")).equals(".wmf")){
                            WmfParser parser = new WmfParser();
                            final SvgGdi gdi = new SvgGdi(false);
                            parser.parse(part.getInputStream(), gdi);

                            org.w3c.dom.Document doc = gdi.getDocument();
                            String svgFile = "C:\\Users\\Administrator\\Desktop\\a.svg";
                            OutputStream out = new FileOutputStream(svgFile);
                            if (svgFile.endsWith(".svgz")) {
                                out = new GZIPOutputStream(out);
                            }
                            output(doc, out);
                            FileInputStream in = new FileInputStream(svgFile);

                            String url = aliyunOSSUtil.uploadInputStream(in, "text/" + UUID.randomUUID() + svgFile.substring(svgFile.lastIndexOf(".")), "image/svg+xml");
                            str.append("<img src=\"" + url + "\">");

                        }else{
                            String url = aliyunOSSUtil.uploadInputStream(part.getInputStream(), "text/" + UUID.randomUUID() + name.substring(name.lastIndexOf(".")), part.getContentType());
                            str.append("<img src=\"" + url + "\">");
                        }

                    }else if (e.selectSingleNode("o:OLEObject")!=null){
                        Element imgElement = (Element) e.selectSingleNode("o:OLEObject");
                        PackagePart part = word.getPartById(imgElement.attribute(imgElement.getQName("r:id")).getValue());
                        String url = aliyunOSSUtil.uploadInputStream(part.getInputStream(), "text/" + UUID.randomUUID() + ".png", "image/png");
                        str.append("<img src=\"" + url + "\">");
                    }
                }else {
                    str.append(handelXml(e, word));
                }
            }

        }
        return str.toString();
    }

    private static void output(org.w3c.dom.Document doc, OutputStream out) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,
                "-//W3C//DTD SVG 1.0//EN");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
                "http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd");
        transformer.transform(new DOMSource(doc), new StreamResult(out));
        out.flush();
        out.close();
    }

    private String handleTable(Element e, XWPFDocument word) throws Exception {
        StringBuffer str = new StringBuffer("");
        List<Element> elementList = e.elements();
        if (e.getQName().getQualifiedName().equals("w:tbl")) {
            str.append("<table border=\"1\" cellspacing=\"0\" cellpadding=\"0\" style=\"width: 450px;\">");
            for (Element ele : elementList) {
                str.append(handleTable(ele, word));
            }
            str.append("</table>");
        } else if (e.getQName().getQualifiedName().equals("w:tr")) {
            str.append("<tr>");
            for (Element ele : elementList) {
                str.append(handleTable(ele, word));
            }
            str.append("</tr>");
        } else if (e.getQName().getQualifiedName().equals("w:tc")) {
            str.append("<td>");
            for (Element ele : elementList) {
                str.append(handleTable(ele, word));
            }
            str.append("</td>");
        } else {
            str.append(handelXml(e, word));
        }
        return str.toString();
    }

    public static String xslConvert(String s, String xslpath, URIResolver uriResolver) {
        TransformerFactory tFac = TransformerFactory.newInstance();
        if (uriResolver != null) tFac.setURIResolver(uriResolver);
        StreamSource xslSource = new StreamSource(xslpath);
        StringWriter writer = new StringWriter();
        try {
            Transformer t = tFac.newTransformer(xslSource);
            Source source = new StreamSource(new StringReader(s));
            Result result = new StreamResult(writer);
            t.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return writer.getBuffer().toString();
    }

    /**
     * <p>Description: 将mathml转为latx </p>
     *
     * @param mml
     * @return
     */
    public String convertMML2Latex(String mml) {
        mml = mml.substring(mml.indexOf("?>") + 2, mml.length()); //去掉xml的头节点
        URIResolver r = new URIResolver() {  //设置xls依赖文件的路径
            @Override
            public Source resolve(String href, String base) throws TransformerException {
                InputStream inputStream = null;
                try {
                    inputStream = new FileInputStream("C:\\Users\\Administrator\\Desktop\\docx2tex-1.6-release\\docx2tex\\mml2tex\\xsl\\" + href);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return new StreamSource(inputStream);
            }
        };
        String latex = xslConvert(mml, "C:\\Users\\Administrator\\Desktop\\docx2tex-1.6-release\\docx2tex\\mml2tex\\xsl\\mml2tex.xsl", r);
        if (latex != null && latex.length() > 1) {
            latex = latex.substring(1, latex.length() - 1);
        }
        return latex;
    }

    /**
     * <p>Description: office mathml转为mml </p>
     *
     * @param xml
     * @return
     */
    public static String convertOMML2MML(String xml) {
        String result = xslConvert(xml, "C:\\Users\\Administrator\\Desktop\\OMML2MML.XSL", null);
        return result;
    }

    /**
     * 提取图片里面的latex公式以及对于没有公式的图片上传到OSS
     *
     * @param htmlStr
     * @param subjectId
     * @return
     */
    public String handleImg(String htmlStr, Integer subjectId) {
        String dataCkeSavedSrc = "data-cke-saved-src\\s*=\\s*\"?(.*?)(\"|>|\\s+)";//匹配data_cke_saved_src
        htmlStr = htmlStr.replaceAll(dataCkeSavedSrc, "")
                .replaceAll("\\r", "<br>");
        String regEx_img = "<img.*?>";//图片链接地址
        String src = "src\\s*=\\s*\"?(.*?)\"(>|\\s+)";//匹配src
        String latex = "data-latex=\"(.*?)\"";//匹配data-latex

        Pattern pattern = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlStr);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            Matcher imgMatch = Pattern.compile(src).matcher(matcher.group());
            Matcher latexMatch = Pattern.compile(latex).matcher(matcher.group());
            //如果能匹配公式
            if (latexMatch.find()) {
                //提取内容
                matcher.appendReplacement(sb, matcher.group().replaceAll(matcher.group(), URLDecoder.decode(latexMatch.group())
                        .replaceAll(latex, "$1").replaceAll("\\$", "").replaceAll("\\\\", "\\\\\\\\\\\\\\\\")));
            } else if (imgMatch.find()) {

            }
        }
        matcher.appendTail(sb);

        return regexRepalce(sb.toString(), subjectId);
    }


    /**
     * 正则替换
     *
     * @param str
     * @return
     */
    private String regexRepalce(String str, int subject) {

        str = str.replaceAll("<input(.*?)>", "<fill></fill>")
                .replaceAll("<!--BA-->.*?<!--EA-->", "<fill></fill>")//填空
                .replaceAll("\\r", "<br>")//换行
                .replaceAll("<u>[&nbsp;]+</u>", "<fill></fill>")//替换填空
                .replaceAll("<u>[ ]+</u>", "<fill></fille>")//替换填空
                .replaceAll("\\$###\\$", "|")//替换分隔符
                .replaceAll("（[ ]+）", "（<span class=\"brack\"></span>）")//替换括号
                .replaceAll("（[ ]+[ ]+）", "（<span class=\"brack\"></span>）");//替换括号

        //处理数理化
        if (subject == 0 || subject == 1 || subject == 2) {
            str = str.replaceAll("<sup(.*?)>(.*?)</sup>", "^{$2}")//替换上角标
                    .replaceAll("<sub(.*?)>(.*?)</sub>", "_{$2}")//替换下角标
                    .replaceAll("<SUP(.*?)>(.*?)</SUP>", "^{$2}")//替换上角标
                    .replaceAll("<SUB(.*?)>(.*?)</SUB>", "_{$2}")//替换下角标
                    .replaceAll("△", "\\\\triangle ")//替换加热符号
                    .replaceAll("⊙", "\\\\odot ")//替换圆符号
                    .replaceAll("∥", "/\\\\!/")//替换平行符号
                    .replaceAll("⊥", "\\\\perp ")//替换垂直符号

                    .replaceAll("∠", "\\\\angle ")//替换∠符号
                    .replaceAll("%", "\\\\% ")//替换%符号
                    .replaceAll("℃", "^\\\\circ{\\\\mathrm{C}}")//℃摄氏度
                    .replaceAll("≌", "\\\\cong ")//≌符号
                    .replaceAll("∽", "\\\\backsim ")//∽符号
                    .replaceAll("Ω", "\\\\Omega ")//Ω符号
                    .replaceAll("•", "\\\\cdot ")//•乘号
                    .replaceAll("°", "^{\\\\circ}")//°度数
                    .replaceAll("≤", "\\\\leqslant ")//小于等于
                    .replaceAll("≠", "\\\\neq ")//小于等于
                    .replaceAll("π", "\\\\pi ")//π
                    .replaceAll("＜", "\\\\lt ")//小于
                    .replaceAll("＞", "\\\\gt ")//大于
                    .replaceAll("dfrac", "frac")
                    .replaceAll("frac", "dfrac")
                    .replaceAll("﹣", "-")
                    .replaceAll("＋", "+");
//                    .replaceAll("\\$", "\\\\\\\\\\$")//
            //处理数学和物理特殊符号
            if (subject == 0 || subject == 1) {
                str = str.replaceAll("sin", "\\\\sin ")//sin
                        .replaceAll("cos", "\\\\cos ")//cos
                        .replaceAll("tan", "\\\\tan ")//tan
                        .replaceAll("cot", "\\\\cot ");//cot
            }
        }

        //内容转换位latex 注：对于html标签不做转换，对于括号内包含html标签的括号不做转换
        str = str2latex(str.replaceAll("\\\\", "\\\\\\\\"), subject);
//        return str.replaceAll("\\\\", "\\\\\\\\");
        return str;
    }

    /**
     * string 转换为 latext
     *
     * @param str
     * @return
     */
    private String str2latex(String str, int subject) {

        if (subject > 2) {
            return str;
        }

        //匹配这些符号加入latex环境中
        String regEx = "[\u0020-\u0100|\uff0b2|…|\u2248|\u224c|\u223d|\u25b1]{0,}[\u0020-\u0027|\u0029-\u0100|\uff0b2|…|\u2248|\u224c|\u223d|\u25b1]+";
        Pattern pat = Pattern.compile(regEx);
        Matcher matcher = pat.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            if (subject == 2) {//化学全部转换位正体
                matcher.appendReplacement(sb, htmlNot2latex(matcher.group(), subject).replaceAll("\\$", "\\\\\\$"));
            } else if (subject == 1 || subject == 0) {//数学和物理
                matcher.appendReplacement(sb, htmlNot2latex(matcher.group(), subject).replaceAll("\\$", "\\\\\\$"));
            } else {
                matcher.appendReplacement(sb, matcher.group().replaceAll("\\$", "\\\\\\$"));
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * html 元素不做公式处理
     */
    private String htmlNot2latex(String str, int subject) {
        //对于html标签和&nbsp;做处理
        String regEx_html = "（<span class=\"brack\"></span>）|<[^>]+>|\\&nbsp;";
        Pattern html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher matcher = html.matcher(str);
        StringBuffer strBuf = new StringBuffer();


        //处理匹配过程中的数据
        while (matcher.find()) {
            matcher.appendReplacement(strBuf, matcher.group().replaceAll("\\$", "\\\\\\$"));
        }

        //最后一个匹配的后面剩余的数据
        StringBuffer appendTailBuf = new StringBuffer();
        matcher.appendTail(appendTailBuf);
        if (subject == 2) { //化学加正体
            if (appendTailBuf.length() > 0) {
                strBuf.append("\\\\(\\\\mathrm{" + appendTailBuf.toString() + "}\\\\)");
            }
        } else {
            if (appendTailBuf.length() > 0) {
                strBuf.append("<span class=\"math-tex\">\\\\(" + appendTailBuf.toString() + "\\\\)</span>");
            }
        }
        String retStr = strBuf.toString();
        return retStr;
    }

    //    @Ignore("")
    @Test
    public void test() {
        try {
            System.out.println(readDoc("C:\\Users\\Administrator\\Desktop\\123\\mathtype2math.docx"));
//            List<String> list = new ArrayList<>();
//            list.add("123");
//            String a = list.get(1);

//            String mml = "<math xmlns=\"http://www.w3.org/1998/Math/MathML\"><mover><mrow><mi>A</mi><mi>B</mi><mi>C</mi></mrow><mstyle mathsize=\"2.07em\"><mo>&#x2322;</mo></mstyle></mover></math>";
//            String omml = xslConvert(mml,"C:\\Users\\Administrator\\Desktop\\MML2OMML.XSL",null);
//
//            System.out.println(omml);
//            System.out.println("\nabc\\nabc".replaceAll("\\n",""));
//            System.out.println(URLEncoder.encode("4 +5= 9"));
//
        } catch (Exception e) {
            try {
                PrintWriter p = new PrintWriter(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\1.txt"));
                e.printStackTrace(p);
                p.flush();
            } catch (FileNotFoundException e1) {

            }
            log.error("错误信息：",e.getMessage());
        }
    }

//    @Autowired
//    CommonRestTemplateUtil tikuApiUtil;
//
//    @Test
//    public void passwordAes() {
////        e9e6aa04b8539b7dde0d9a3fdc798541
////        e9e6aa04b8539b7dde0d9a3fdc798541
//        HashMap<String, Object> param = new HashMap<>();
//        param.put("password", "admin1");
//        Map map = tikuApiUtil.apiGateway3(ApiConstant.USER_PASSWORD_AES, ApiConstant.USER_PASSWORD_AES_APIID, param, HttpMethod.POST);
//        if (map != null && map.get("data") != null) {
//            System.out.println(map.get("data").toString());
//        }
//    }


//    @Autowired
//    private AutoMarkQuesService autoMarkQuesService;
//    @Test
//    public void markQues() {
//        autoMarkQuesService.markQuesByQId(18650L);
//        System.out.println(autoMarkQuesService.getAutoMarkDataByQid(18650L));
//    }

    public static void main(String[] args) {
        // \u000d System.out.println("test");
    }

}

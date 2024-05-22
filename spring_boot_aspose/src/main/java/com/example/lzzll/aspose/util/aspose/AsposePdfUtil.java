package com.example.lzzll.aspose.util.aspose;

import com.aspose.words.BreakType;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.SaveFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.InputStream;

/**
 * @Author lf
 * @Date 2024/5/8 10:27
 * @Description:
 */
public class AsposePdfUtil {

    private final static Logger log = LoggerFactory.getLogger(AsposePdfUtil.class);

    /**
     * 将图片和文本交错生成pdf
     * @param picPath  图片路径
     * @param text 文本内容
     * @param pdfPath 生成pdf的保存地址
     */
    public static void generatePdf(String picPath,String text,String pdfPath){
        Document document = null;
        DocumentBuilder documentBuilder = null;
        try {
            getWordLicense();
            String path = ResourceUtils.getURL("classpath:").getPath();
//            Document document = new Document("/alidata1/server/apache-tomcat-9.0.12/webapps/paper-generator/WEB-INF/classes/template/Temp_No_Seal_Line_A4.docx");
            log.info("path===>" + path);
            String currentOSName = System.getProperty("os.name").toLowerCase();
            log.info("Current OS name ===> " + currentOSName);
            if(!StringUtils.isEmpty(currentOSName) && currentOSName.startsWith("windows")){
                path = path.substring(1);
            }
            document = new Document(path+"/template/Temp_No_Seal_Line_A4.docx");
            documentBuilder = new DocumentBuilder(document);
            documentBuilder.writeln("正文标题");
            for (int i = 0; i < 3; i++) {
                documentBuilder.insertImage(picPath);
                documentBuilder.insertBreak(BreakType.PARAGRAPH_BREAK);
                documentBuilder.writeln(text);
            }
            document.save(pdfPath, SaveFormat.PDF);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (document != null){
                try {
                    document.cleanup();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 验证word License 若不验证会有水印
     * @return
     */
    private static boolean getWordLicense() {
        boolean result = false;
        try {
            InputStream is = ImageToPdfUtil.class.getClassLoader().getResourceAsStream("aspose.word.license.xml");
            com.aspose.words.License license = new com.aspose.words.License();
            license.setLicense(is);
            result = true;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }


    public static void main(String[] args) {
        generatePdf("http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/smart-books/png/d3b6df4c-3cec-48fc-9325-41f9f7e6f27a.png","\u200B\n" +
                "2\n" +
                "古诗词古诗词古诗词古诗词\n" +
                "\n" +
                "古诗词古诗词古诗词古诗词古诗词古诗词古诗词古诗词古诗词古诗词古诗词古诗词古诗词古诗词古诗词古诗词古诗词古诗词古诗词古诗词","C:\\Users\\lf\\Desktop\\testSave.pdf");
    }

}

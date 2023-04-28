package com.example.lzzll.javastudy.pdfbox;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.aspose.words.SaveFormat;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

/**
 * @Author lf
 * @Date 2023/4/27 16:03
 * @Description:
 */
@Slf4j
public class Word2PdfTest {

    public static void main(String[] args) {
        String inputFile = "C:\\Users\\lf\\Desktop\\docx2pdf\\mathtable.docx";
//        String inputFile = "C:\\Users\\lf\\Desktop\\docx2pdf\\chinese.docx";
        String outputFile = "C:\\Users\\lf\\Desktop\\docx2pdf\\444.pdf";
        // 方式一
//        itextPdfWord2Pdf(inputFile,outputFile);
        // 方式二
//        asposeWord2Pdf(inputFile,outputFile);
        // 方式三
        office2PDF(inputFile,outputFile);
        // 方式四

    }


    /**
     * 通过itext工具将word转为pdf
     *    优点：实现简单
     *    缺点：效果非常差
     * @param inputFile
     * @param outputFile
     */
    private static void itextPdfWord2Pdf(String inputFile,String outputFile){
        try {
            // 读取Word文档
            FileInputStream fis = new FileInputStream(inputFile);
            XWPFDocument document = new XWPFDocument(fis);

            // 写入PDF文件
            OutputStream out = new FileOutputStream(new File(outputFile));
            Document pdfDoc = new Document();
            PdfWriter.getInstance(pdfDoc, out);
            pdfDoc.open();
            for (int i = 0; i < document.getParagraphs().size(); i++) {
                Paragraph para = new Paragraph(document.getParagraphs().get(i).getText());
                pdfDoc.add(para);
            }
            pdfDoc.close();
            out.close();
            fis.close();

            System.out.println("Word文档已成功转换为PDF文件！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过aspose工具将word转为pdf
     *   优点：效果比较好，实现较为简单，引入aspose的依赖即可
     *   缺点：对于语文中的着重符，公式表格的转换能力比较差
     * @param inputFile
     * @param outputFile
     */
    private static void asposeWord2Pdf(String inputFile,String outputFile){
        try {
            com.aspose.words.Document doc = new com.aspose.words.Document(inputFile);
            doc.save(outputFile, SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过openOffice将docx文档转为pdf文档
     *    需要安装openoffice服务，docx版本无法转pdf文档，会报"inputFormat is null"错误；doc版本转pdf时数学表格会出现缩略现象
     *    启动openoffice方法
     *      1、需要先进入C盘的安装目录  C:\Windows\System32\cmd.exe
     *      2、地址栏输入cmd打开命令窗口后输入 soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;" –nofirststartwizard 命令启动openoffice服务
     *      3、通过 netstat -ano | findStr '8100' 查看是否启动成功
     * @param sourceFile
     * @param destFile
     * @return
     */
    public static int office2PDF(String sourceFile, String destFile) {
        if(StringUtils.isEmpty(sourceFile) || StringUtils.isEmpty(destFile) ){
            log.debug("文件路径为空");
            return -1;
        }
        log.debug("sourceFile===========" + sourceFile);
        log.debug("destFile===========" + destFile);

        try {
            File inputFile = new File(sourceFile);
            if (!inputFile.exists()) {
                log.debug("没有文档===========");
                return -1;// 找不到源文件, 则返回-1
            }
            // 如果目标路径不存在, 则新建该路径
            File outputFile = new File(destFile);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }
            // 文件输入输出流
            FileInputStream in = null;
            FileOutputStream out = null;
            in = new FileInputStream(inputFile);
            out = new FileOutputStream(outputFile);

            if(in == null){
                log.debug("文件输入流为空===========");
                return -1;
            }
            if(out == null){
                log.debug("文件输出流为空===========");
                return -1;
            }
            // 创建连接 服务对应的ip地址和端口号
            OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);

            //OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
            connection.connect();

            // 创建转换器
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            // 文件格式
            DefaultDocumentFormatRegistry factory = new DefaultDocumentFormatRegistry();

            // 待转换文件名称及扩展名称
            String officeName = inputFile.getName();
            String officeExt = officeName.substring(officeName.lastIndexOf(".") + 1);
            // 待转换文件 转换文件格式对象
            DocumentFormat officeFormat = factory.getFormatByFileExtension(officeExt);
            DocumentFormat pdfFormat = factory.getFormatByFileExtension("pdf");
            log.debug("开始将word转为PDF===========");

            // 记录转换时间 咨询转换
            long startTime = System.currentTimeMillis();
            converter.convert(in, officeFormat, out, pdfFormat);
//            converter.convert(in, out);
            long endTime = System.currentTimeMillis();
            log.debug("转换时间：" + (endTime - startTime) + "ms");

            //断开连接
            connection.disconnect();
            return 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.debug("FileNotFoundException===========" + e.getMessage() );
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            log.debug("IOException===========" + e.getMessage());

            return -1;
        } catch (Exception e){
            e.printStackTrace();
            log.debug("Exception===========" + e.getMessage());
            return -1;
        }
//        return 1;
    }


    private static int libreoffice(String sourceFile, String destFile){
        try {
            Runtime run = Runtime.getRuntime();
            Process process1 = run.exec(String.format("libreoffice --invisible --math --convert-to pdf --outdir %s %s", destFile, sourceFile));
            if (process1.waitFor() != 0){
                return -1;
            }
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }


}

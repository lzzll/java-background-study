package com.example.lzzll.aspose.util.aspose;

import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author lf
 * @Date 2022/11/1 9:37
 * @Description:
 */
@Slf4j
public class PptToPdfUtil {

    /**
     * 将ppt文档转化为pdf文档，转换效果不好
     * @param pptUrl ppt文档路径
     * @param pdfUrl pdf文档
     * @return
     */
//    public static void pptToPdf(String pptUrl,String pdfUrl){
//        Document document = null;
//        HSLFSlideShow hslfSlideShow = null;
//        PdfWriter pdfWriter = null;
//        InputStream inputStream = null;
//        FileOutputStream outputStream =  null;
//        Graphics2D graphics2d = null;
//        try {
//            // 当作一个URL来装载文件
//            URL url = new URL(pptUrl);
//            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//            //设置超时间为3秒`
//            conn.setConnectTimeout(3*1000);
//            //得到输入流
//            inputStream = conn.getInputStream();
//            hslfSlideShow = new HSLFSlideShow(inputStream);
//            // 获取临时地址的输出流
//            outputStream = new FileOutputStream(pdfUrl);
//            // 获取ppt文件页面
//            Dimension dimension = hslfSlideShow.getPageSize();
//            // 打开pdf文档
//            document = new Document(new com.itextpdf.text.Rectangle( PageSize.A4.getHeight(),PageSize.A4.getWidth()), 0, 0, 0, 0);
//            // pdfWriter实例
//            pdfWriter = PdfWriter.getInstance(document, outputStream);
//            document.open();
////            PdfPTable pdfPTable = new PdfPTable(1);
//            java.util.List<HSLFSlide> hslfSlideList = hslfSlideShow.getSlides();
//            // 将每一页ppt写入pdf文档中
//            for (int i=0; i < hslfSlideList.size(); i++) {
//                HSLFSlide hslfSlide = hslfSlideList.get(i);
//                // 设置字体, 解决中文乱码
//                for (HSLFShape shape : hslfSlide.getShapes()) {
//                    HSLFTextShape textShape = (HSLFTextShape) shape;
//                    for (HSLFTextParagraph textParagraph : textShape.getTextParagraphs()) {
//                        for (HSLFTextRun textRun : textParagraph.getTextRuns()) {
//                            textRun.setFontFamily("宋体");
//                        }
//                    }
//                }
//                BufferedImage bufferedImage = new BufferedImage((int)dimension.getWidth(), (int)dimension.getHeight(), BufferedImage.TYPE_INT_RGB);
////                BufferedImage bufferedImage = new BufferedImage((int)PageSize.A4.getHeight(), (int)PageSize.A4.getWidth(), BufferedImage.TYPE_INT_RGB);
//
//                graphics2d = bufferedImage.createGraphics();
//
//                graphics2d.setPaint(Color.white);
//                graphics2d.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 12));
//
//                hslfSlide.draw(graphics2d);
//
//                com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(bufferedImage, null);
////                image.scalePercent(50f);
//
//                // 写入单元格
////                pdfPTable.addCell(new PdfPCell(image, true));
//                // 每一页ppt放一个空白的pdf页面
//                document.newPage();
//                document.add(image);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (graphics2d != null){
//                graphics2d.dispose();
//            }
//            if (document != null) {
//                document.close();
//            }
//            if (pdfWriter != null) {
//                pdfWriter.close();
//            }
//            if (outputStream != null){
//                try {
//                    outputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (inputStream != null){
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println("ppt转换完毕");
//    }

    /**
     *  将pptx格式的文档转化为pdf格式文档，转换效果不好
     * @param pptxUrl
     * @param pdfUrl
     * @return
     */
//    public static boolean pptxToPdf(String pptxUrl,String pdfUrl){
//        Document document = null;
//        XMLSlideShow slideShow = null;
//        PdfWriter pdfWriter = null;
//        FileOutputStream outputStream =  null;
//        InputStream inputStream = null;
//        Graphics2D graphics2d = null;
//        try {
//            // 当作一个URL来装载文件
//            URL url = new URL(pptxUrl);
//            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//            //设置超时间为3秒`
//            conn.setConnectTimeout(3*1000);
//            //得到输入流
//            inputStream = conn.getInputStream();
//            slideShow = new XMLSlideShow(inputStream);
//            Dimension dimension = slideShow.getPageSize();
//            // 竖版pdf
////            document = new Document();
//            // 横板pdf
//            document = new Document(new com.itextpdf.text.Rectangle( PageSize.A4.getHeight(),PageSize.A4.getWidth()), 0, 0, 0, 0);
//            outputStream = new FileOutputStream(new File(pdfUrl));
//            pdfWriter = PdfWriter.getInstance(document, outputStream);
//            // 打开pdf
//            document.open();
//
////            PdfPTable pdfPTable = new PdfPTable(1);
//
//            List<XSLFSlide> slideList = slideShow.getSlides();
//
//            for (int i = 0, row = slideList.size(); i < row; i++) {
//                XSLFSlide slide = slideList.get(i);
//                // 设置字体, 解决中文乱码
//                for (XSLFShape shape : slide.getShapes()) {
//                    XSLFTextShape textShape = (XSLFTextShape) shape;
//                    for (XSLFTextParagraph textParagraph : textShape.getTextParagraphs()) {
//                        for (XSLFTextRun textRun : textParagraph.getTextRuns()) {
//                            textRun.setFontFamily("宋体");
//                        }
//                    }
//                }
//                BufferedImage bufferedImage = new BufferedImage((int)dimension.getWidth(), (int)dimension.getHeight(), BufferedImage.TYPE_INT_RGB);
////                BufferedImage bufferedImage = new BufferedImage((int)PageSize.A4.getHeight(), (int)PageSize.A4.getWidth(), BufferedImage.TYPE_INT_RGB);
//                graphics2d = bufferedImage.createGraphics();
//                graphics2d.setPaint(Color.white);
//                graphics2d.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 12));
//                slide.draw(graphics2d);
//                com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(bufferedImage, null);
//                image.scalePercent(50f);
//                // 写入单元格
////                pdfPTable.addCell(new PdfPCell(image, true));
//                document.newPage();
//                document.add(image);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            if (graphics2d != null){
//                graphics2d.dispose();
//            }
//            if (document != null) {
//                document.close();
//            }
//            if (pdfWriter != null) {
//                pdfWriter.close();
//            }
//            if (outputStream != null){
//                try {
//                    outputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (inputStream != null){
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println("pptx转换完毕");
//        return true;
//    }




    /**
     * 将ppt和pptx格式文档转换为pdf文档，效果较好，但是比较耗时
     * @param pptxUrl
     * @param pdfUrl
     * @return
     */
    public static void pptxToPdf(String pptxUrl,String pdfUrl){
        // 验证License
        if (!getPptLicense()) {
            return;
        }
        FileOutputStream outputStream =  null;
        InputStream slides = null;
        try {
            long old = System.currentTimeMillis();
            // 当作一个URL来装载文件
            URL url = new URL(pptxUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时间为3秒`
            conn.setConnectTimeout(3*1000);
            //得到输入流
            slides = conn.getInputStream();
            Presentation pres = new Presentation(slides);
            // 输出pdf路径
            File file = new File(pdfUrl);
            outputStream = new FileOutputStream(file);
            pres.save(outputStream, SaveFormat.Pdf);
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒\n\n" + "文件临时保存在:" + file.getPath());
        }catch (Exception e){
            log.error("ppt转换pdf失败。ppt路径为:{},pdf路径为:{}",pptxUrl,pdfUrl);
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (slides != null){
                try {
                    slides.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 验证ppt License 若不验证会有水印
     * @return
     */
    private static boolean getPptLicense() {
        boolean result = false;
        InputStream is = null;
        try {
            is = ImageToPdfUtil.class.getClassLoader().getResourceAsStream("license.xml");
            com.aspose.slides.License license = new com.aspose.slides.License();
            license.setLicense(is);
            result = true;
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        // 转换效果较差
        // ppt转化为pdf  https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/pptTest.ppt
//        pptToPdf("https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/pptTest.ppt","C:\\Users\\lf\\Desktop\\pptToPdfTest2.pdf");
        // pptx转化为pdf  https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/pptTest.pptx
//        pptxToPdf("https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/pptTest.pptx","C:\\Users\\lf\\Desktop\\pptToPdfTest3.pdf");

        // 转换效果比较好，但是比较耗时
        pptxToPdf("https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/pptTest.pptx","C:\\Users\\lf\\Desktop\\pptToPdfTest4.pdf");
    }


}

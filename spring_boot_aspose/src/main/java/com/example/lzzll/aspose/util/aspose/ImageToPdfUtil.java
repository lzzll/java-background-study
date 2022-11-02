package com.example.lzzll.aspose.util.aspose;

import com.aspose.words.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.UUID;

/**
 * @Author lf
 * @Date 2022/10/26 15:07
 * @Description: 图片转文档
 */
@Slf4j
public class ImageToPdfUtil {

    // 临时文件存储目录
    public static final String TEMPORARY_FILE = "/canpointnas/cpcloud/tmpfiles/smartBook/";

    /**
     * 获取文件名称,带后缀
     * @param fileDownloadUrl
     * @return
     */
    public static String getOriFileName(String fileDownloadUrl) {
        if (!StringUtils.isEmpty(fileDownloadUrl)){
            return fileDownloadUrl.substring(fileDownloadUrl.lastIndexOf("/")+1);
        }
        return null;
    }

    /**
     * 将单张图片转成pdf文档输出。根据图片的长宽判断使用横板还是竖版
     * @param standardWidth 文档的标准宽
     * @param standardHeight 文档的标准高
     * @param imgUrl 图片输入路径
     * @param saveUrl 保存路径
     * @return
     */
    public static void imageToPdf(float standardWidth,float standardHeight,String imgUrl,String saveUrl){
        String tempFileUrl = null;
        Graphics g = null;
        Document doc = null;
        try {
            // 获取图片的文件扩展名(采用jpg格式的图片会导致生成的图片颜色失真)
//            String extFileName = imgUrl.substring(imgUrl.lastIndexOf(".")+1);
            // 将新的图片文件写入到指定的文件夹中
            if (!new File(TEMPORARY_FILE).exists()){
                new File(TEMPORARY_FILE).mkdirs();
            }
            tempFileUrl = TEMPORARY_FILE+ UUID.randomUUID().toString().replace("-","")+".png";
            BufferedImage img = ImageIO.read(new File(imgUrl));//读取图片1
            int height = img.getHeight();
            int width = img.getWidth();
            // 判断是创建横板的A4文档还是竖版的A4文档，宽高比小于A4版的宽高比就是横板，否则就是竖版
            boolean directionFlag = judgePdfDirection(height, width, standardWidth / standardHeight);
            if (directionFlag){
                doc = new Document(new Rectangle(standardWidth,standardHeight), 0, 0, 0, 0); //new一个竖版的pdf文档
            }else {
                doc = new Document(new Rectangle(standardHeight,standardWidth), 0, 0, 0, 0); //new一个横版的pdf文档
            }
            // 获取图片的放缩比例
            float scaleRatio = getScaleRatio(directionFlag, height, width, standardHeight, standardWidth);
            Image resultImg = img.getScaledInstance(Double.valueOf(Math.floor(width * scaleRatio)).intValue(), Double.valueOf(Math.floor(height * scaleRatio)).intValue(), Image.SCALE_DEFAULT);
            // 5、将Image类型转换成BufferedImage对象[BufferedImage.TYPE_INT_ARGB：表示具有8位RGBA颜色成分的整数像素的图像]
            BufferedImage newImage = new BufferedImage(Double.valueOf(width*scaleRatio).intValue(),Double.valueOf(height * scaleRatio).intValue(),BufferedImage.TYPE_INT_ARGB);
            // 一个新的图形上下文，这是这个图形上下文的副本
            g = newImage.getGraphics();
            // 绘制图片大小
            g.drawImage(resultImg, 0, 0, null);
            ImageIO.write(newImage,"png",new File(tempFileUrl));
            // 将图片写入pdf文档中
            com.itextpdf.text.Image png = com.itextpdf.text.Image.getInstance(tempFileUrl);
            png.setAlignment(com.itextpdf.text.Image.MIDDLE);
            // pdf写入
            PdfWriter.getInstance(doc, new FileOutputStream(saveUrl));
            doc.open(); //打开文档
            doc.add(png);
            // 创建输出文件
            new File(saveUrl).createNewFile();
        } catch (Exception e) {
            log.error("图片转文档失败:"+e.getMessage(), ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
            // 释放文件资源
            if (g != null){
                g.dispose();
            }
            // 删除临时文件
            if (!StringUtils.isEmpty(tempFileUrl) && new File(tempFileUrl).exists()){
                new File(tempFileUrl).delete();
            }
            // 关闭文档
            if (doc != null){
                doc.close();
            }
        }
    }

    /**
     * 判断创建的pdf是横版还是竖版：true 竖版 false 横版
     * @param height
     * @param width
     * @param ratio
     * @return
     */
    private static boolean judgePdfDirection(int height,int width,float ratio){
        if (height == 0){
            return true;
        }
        // 宽高比小于标准比例就是竖版
        return width / height <= ratio;
    }

    /**
     * 获取缩放的比例
     * @param direction true 竖版 false 横版
     * @param height 图片高
     * @param width 图片宽
     * @param standardHeight 标准高
     * @param standardWidth  标准宽
     * @return
     */
    private static float getScaleRatio(boolean direction,int height,int width,float standardHeight,float standardWidth){
        // 竖版时比较标准宽和图片宽的比例
        if (direction){
            return standardWidth/width;
        }else {
            // 横板时比较标准高和图片高的比例
            return standardHeight/height;
        }
    }


    /**
     * 将多张图片转成pdf文档输出
     * @param standardWidth 文档的标准宽
     * @param standardHeight 文档的标准高
     * @param imgUrls 多张图片的输入路径
     * @param saveUrl 保存路径
     * @param localOrNet true 本地 false 线上
     * @return
     */
    public static void imagesToPdf(float standardWidth,float standardHeight,List<String> imgUrls,String saveUrl,boolean localOrNet){
        String tempFileUrl = null;
        Graphics2D g = null;
        Document doc = null;
        BufferedImage img = null;
        try {
            // 将新的图片文件写入到指定的文件夹中
            if (!new File(TEMPORARY_FILE).exists()){
                new File(TEMPORARY_FILE).mkdirs();
            }
            tempFileUrl = TEMPORARY_FILE+ UUID.randomUUID().toString().replace("-","")+".png";
            // 文档按照传入的宽高生成固定的文档
            doc = new Document(new Rectangle(standardWidth,standardHeight), 0, 0, 0, 0); //new一个竖版的pdf文档
            // pdf写入
            PdfWriter.getInstance(doc, new FileOutputStream(saveUrl));
            doc.open(); //打开文档
            if (imgUrls != null && imgUrls.size() != 0){
                for (String imgUrl : imgUrls) {
                    if (localOrNet){
                        img = ImageIO.read(new File(imgUrl));//读取图片1
                    }else {
                        URLConnection conn = new URL(imgUrl).openConnection();
                        conn.setConnectTimeout(3000);
                        img = ImageIO.read(conn.getInputStream());
                    }
                    int height = img.getHeight();
                    int width = img.getWidth();
                    // 获取图片的放缩比例
                    float scaleRatio = getScaleRatio(true, height, width, standardHeight, standardWidth);
//                    log.info("放缩后x的值:{},放缩后y的值:{}",Double.valueOf(Math.floor(width * scaleRatio)).intValue(),Double.valueOf(Math.floor(height * scaleRatio)).intValue());
                    Image resultImg = img.getScaledInstance(Double.valueOf(Math.floor(width * scaleRatio)).intValue(), Double.valueOf(Math.floor(height * scaleRatio)).intValue(), Image.SCALE_DEFAULT);
                    // 5、将Image类型转换成BufferedImage对象[BufferedImage.TYPE_INT_ARGB：表示具有8位RGBA颜色成分的整数像素的图像]
                    BufferedImage newImage = new BufferedImage(Double.valueOf(standardWidth).intValue(),Double.valueOf(standardHeight).intValue(),BufferedImage.TYPE_INT_ARGB);
                    // 一个新的图形上下文，这是这个图形上下文的副本
                    g = newImage.createGraphics();
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                    log.info("起始点X坐标:{},起始点y坐标:{}",Double.valueOf((standardWidth-resultImg.getWidth(null))/2).intValue(),Double.valueOf((standardHeight-resultImg.getHeight(null))/2).intValue());
                    // 居中画图
                    g.drawImage(resultImg, Double.valueOf((standardWidth-resultImg.getWidth(null))/2).intValue(), Double.valueOf((standardHeight-resultImg.getHeight(null))/2).intValue(), null);
//                    g.drawImage(resultImg, 0, 0, null);
                    ImageIO.write(newImage,"png",new File(tempFileUrl));
                    // 将图片写入pdf文档中
                    com.itextpdf.text.Image png = com.itextpdf.text.Image.getInstance(tempFileUrl);
                    png.setAlignment(com.itextpdf.text.Image.MIDDLE);
                    // 创建新的文档页
                    doc.newPage();
                    doc.add(png);
                }
            }
            // 创建输出文件
            new File(saveUrl).createNewFile();
        } catch (Exception e) {
            log.error("图片转文档失败:"+e.getMessage(), ExceptionUtils.getFullStackTrace(e));
            e.printStackTrace();
        }finally {
            // 释放文件资源
            if (g != null){
                g.dispose();
            }
            // 删除临时文件
            if (!StringUtils.isEmpty(tempFileUrl) && new File(tempFileUrl).exists()){
                new File(tempFileUrl).delete();
            }
            // 关闭文档
            if (doc != null){
                doc.close();
            }
        }
    }


    /**
     * 将用户上传的图片文件处理成按指定宽高居中的图片并保存到本地
     * @param ins 文件输入流
     * @param standardWidth 标准宽
     * @param standardHeight 标准高
     * @param localTempUrl 本地文件地址
     * @param extName 文件扩展名
     */
    public static void handleImgToMiddle(InputStream ins, Integer standardWidth, Integer standardHeight, String localTempUrl, String extName) {
        Graphics2D g = null;
        try {
            if (".png".equalsIgnoreCase(extName) || ".jpg".equalsIgnoreCase(extName) || ".jpeg".equalsIgnoreCase(extName)){
                BufferedImage img = ImageIO.read(ins);
                int height = img.getHeight();
                int width = img.getWidth();
                // 获取图片的放缩比例
                float scaleRatio = getScaleRatio(true, height, width, standardHeight, standardWidth);
                Image resultImg = img.getScaledInstance(Double.valueOf(Math.floor(width * scaleRatio)).intValue(), Double.valueOf(Math.floor(height * scaleRatio)).intValue(), Image.SCALE_DEFAULT);
                // 5、将Image类型转换成BufferedImage对象[BufferedImage.TYPE_INT_ARGB：表示具有8位RGBA颜色成分的整数像素的图像]
                BufferedImage newImage = new BufferedImage(Double.valueOf(standardWidth).intValue(),Double.valueOf(standardHeight).intValue(),BufferedImage.TYPE_INT_ARGB);
                // 一个新的图形上下文，这是这个图形上下文的副本
                g = newImage.createGraphics();
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // 居中画图
                g.drawImage(resultImg, Double.valueOf((standardWidth-resultImg.getWidth(null))/2).intValue(), Double.valueOf((standardHeight-resultImg.getHeight(null))/2).intValue(), null);
//                ImageIO.write(newImage,extName.substring(extName.lastIndexOf(".")+1),new File(localTempUrl));
                ImageIO.write(newImage,"png",new File(localTempUrl));
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("处理图片居中失败:"+e.getMessage(),ExceptionUtils.getFullStackTrace(e));
        }finally {
            if (g != null){
                g.dispose();
            }
            if (ins != null){
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 文件大小--路径
     *
     * @return
     */
    public static long getFileLength(String downloadUrl) {
        if(downloadUrl == null || "".equals(downloadUrl)){
            return 0L ;
        }
        URL url = null;
        try {
            url = new URL(downloadUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return 0L;
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows 7; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36 YNoteCef/5.8.0.1 (Windows)");
            return (long) conn.getContentLength();
        } catch (IOException e) {
            return 0L;
        } finally {
            conn.disconnect();
        }
    }

    /**
     * 根据文件路径计算文件页数
     *
     * @return
     */
    public static int getFilePages(String downloadUrl) {
        // 获取文件的扩展名
        String extName = downloadUrl.substring(downloadUrl.lastIndexOf(".") + 1);
        if ("jpg".equalsIgnoreCase(extName) || "jpeg".equalsIgnoreCase(extName) || "png".equalsIgnoreCase(extName)){
            // 图片的页数默认设置为1
            return 1;

        }else if (extName.equalsIgnoreCase("doc") || extName.equalsIgnoreCase("docx")){
            // 文档的页数需要具体读取
            //输入流
            InputStream inputStream = null;
            try {
                // 当作一个URL来装载文件
                URL url = new URL(downloadUrl);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                //设置超时间为3秒`
                conn.setConnectTimeout(3*1000);
                //得到输入流
                inputStream = conn.getInputStream();
                com.aspose.words.Document document = new com.aspose.words.Document(inputStream);
                return document.getPageCount();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    // 关闭输出流
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }else if (extName.equalsIgnoreCase("pdf")){
            // 内存中存储的PDF Document
            PDDocument pdDocument = null;
            //输入流
            InputStream inputStream = null;
            try {
                // 当作一个URL来装载文件
                URL url = new URL(downloadUrl);
                URLConnection con = url.openConnection();
                con.setConnectTimeout(3 * 1000);
                inputStream = con.getInputStream();
                pdDocument = PDDocument.load(inputStream);
                // 获取页码
                return pdDocument.getNumberOfPages();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    // 关闭输出流
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (pdDocument != null) {
                    // 关闭PDF Document
                    try {
                        pdDocument.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        // 将图片写到标准的A4文档上
//        imageToPdf(PageSize.A4.getWidth(),PageSize.A4.getHeight(), "C:\\Users\\lf\\Desktop\\test7.jpg","C:\\Users\\lf\\Desktop\\ttt5.pdf");
//        long fileLength = getFileLength("http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/content-plans/splitPdf/b2bd9ccc-85eb-4479-bc95-19c668b95370.pdf");

        // png图片格式
//        long fileLength = getFileLength("http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/pre-task-img/png/97776345-c209-4970-a285-0c3e57b4e235.png");
        // docx格式 https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/2.docx
//        long fileLength = getFileLength("https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/2.docx");
        // doc格式  https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/docTest.doc
//        long fileLength = getFileLength("https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/docTest.doc");
        // pdf格式  http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/content-plans/splitPdf/d08398cb-ee85-45df-95d4-2e01404d219e.pdf
//        long fileLength = getFileLength("http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/content-plans/splitPdf/d08398cb-ee85-45df-95d4-2e01404d219e.pdf");
//        System.out.println(fileLength);

        // docx格式
//        int pages = getFilePages("https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/2.docx");
        // doc格式  https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/docTest.doc
//        int pages = getFilePages("https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/docTest.doc");
        // pdf格式 http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/content-plans/splitPdf/d08398cb-ee85-45df-95d4-2e01404d219e.pdf
//        int pages = getFilePages("http://qp-tiku-test.oss-cn-beijing.aliyuncs.com/content-plans/splitPdf/d08398cb-ee85-45df-95d4-2e01404d219e.pdf");
//        System.out.println(pages);

//        System.out.println(getOriFileName("https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/excelTest.xlsx"));


        // 将多张图片转化为文档
//        List<String> imgLists = Lists.newArrayList();
//        imgLists.add("C:\\Users\\lf\\Desktop\\test3.png");
//        imgLists.add("C:\\Users\\lf\\Desktop\\test3.png");
////        imgLists.add("C:\\Users\\lf\\Desktop\\test8.jpg");
//        imagesToPdf(PageSize.A4.getWidth(),PageSize.A4.getHeight(), imgLists,"C:\\Users\\lf\\Desktop\\t1.pdf",true);

    }

}

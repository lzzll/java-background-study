package com.example.lzzll.javastudy.pdfbox;//package com.example.lzzll.pdfbox;
//
//import com.example.lzzll.pdfbox.domain.PdfDomainVO;
//import com.example.lzzll.pdfbox.pdfutil.PdfUtil;
//import org.junit.Test;
//
//import java.io.*;
//
///**
// * @Author lf
// * @Date 2019/9/24 14:30
// * @Description:
// */
//public class PdfTest {
//
//    private static InputStream inputStream = null;
//    private static String inputFilePath = "C:\\Users\\lf\\Desktop\\test\\ElasticSearch.pdf";
//    private static String outputFilePath = "C:\\Users\\lf\\Desktop\\test\\abc.pdf";
//    static {
//
//        try {
//            File file = new File(inputFilePath);
//            inputStream = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // 测试创建一个pdf的空白文档，其中有多个空白页面
//    @Test
//    public void test1() throws IOException {
//        PdfUtil.createBlank(outputFilePath);
//    }
//
//    // 测试读取pdf文件中的所有中文信息
//    @Test
//    public void test2(){
//        PdfUtil.READPDF(inputFilePath);
//    }
//
//    // 测试按顺序读取pdf文件中的信息
//    @Test
//    public void test3(){
//        PdfDomainVO vo = new PdfDomainVO();
//        vo.setInputfile(inputFilePath);
//        vo.setPagestart(10);
//        vo.setPageend(20);
//        PdfDomainVO returnVo = PdfUtil.readPageNO(vo);
//        System.out.println(returnVo.getContent());
//    }
//
//    // 生成pdf文件并写入内容,写入的内容不支持中文
//    @Test
//    public void test4(){
//        try {
////            String editWord = "难道pdfBox真的不支持中文吗！";
//            String editWord = "why the pdfbox not adapt to chinese!";
//            PdfUtil.createPdfwithContent(outputFilePath,editWord);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}

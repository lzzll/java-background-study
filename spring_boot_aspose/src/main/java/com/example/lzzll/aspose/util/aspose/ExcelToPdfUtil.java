package com.example.lzzll.aspose.util.aspose;

import com.aspose.cells.Workbook;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author lf
 * @Date 2022/11/1 9:41
 * @Description:
 */
@Slf4j
public class ExcelToPdfUtil {

    /**
     * 将excel文档转化为pdf文档
     * @param excelUrl excel文档路径
     * @param pdfUrl pdf文档
     * @return
     */
    public static void excelToPdf(String excelUrl,String pdfUrl) {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            // 当作一个URL来装载文件
            URL url = new URL(excelUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒`
            conn.setConnectTimeout(3*1000);
            //得到输入流
            inputStream = conn.getInputStream();
            // 验证License 若不验证则转化出的pdf文档会有水印产生
            getExcelLicense();
            Workbook wb = new Workbook(inputStream);
            outputStream = new FileOutputStream(pdfUrl);
            com.aspose.cells.PdfSaveOptions pdfSaveOptions = new com.aspose.cells.PdfSaveOptions();
            // 设置为true就不对表格做拆分处理，否则会将表格拆分为两页
            pdfSaveOptions.setOnePagePerSheet(true);
            int[] autoDrawSheets={3};
            //当excel中对应的sheet页宽度太大时，在PDF中会拆断并分页。此处等比缩放。
            autoDraw(wb,autoDrawSheets);
            int[] showSheets={0};
            //隐藏workbook中不需要的sheet页。
            printSheetPage(wb,showSheets);
            wb.save(outputStream, pdfSaveOptions);
        } catch (Exception e) {
            log.error("将ppt文档转化为pdf文档失败:"+e.getMessage(), ExceptionUtils.getFullStackTrace(e));
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 设置打印的sheet 自动拉伸比例
     * @param wb
     * @param page 自动拉伸的页的sheet数组
     */
    public static void autoDraw(com.aspose.cells.Workbook wb,int[] page){
        if(null!=page&&page.length>0){
            for (int i = 0; i < page.length; i++) {
                wb.getWorksheets().get(i).getHorizontalPageBreaks().clear();
                wb.getWorksheets().get(i).getVerticalPageBreaks().clear();
            }
        }
    }

    /**
     * 隐藏workbook中不需要的sheet页。
     *
     * @param wb
     * @param page 显示页的sheet数组
     */
    public static void printSheetPage(com.aspose.cells.Workbook wb, int[] page) {
        for (int i = 1; i < wb.getWorksheets().getCount(); i++) {
            wb.getWorksheets().get(i).setVisible(false);
        }
        if (null == page || page.length == 0) {
            wb.getWorksheets().get(0).setVisible(true);
        } else {
            for (int i = 0; i < page.length; i++) {
                wb.getWorksheets().get(i).setVisible(true);
            }
        }
    }

    /**
     * 验证excel License 若不验证会有水印
     * @return
     */
    private static boolean getExcelLicense() {
        boolean result = false;
        InputStream is = null;
        try {
            is = ImageToPdfUtil.class.getClassLoader().getResourceAsStream("license.xml");
            com.aspose.cells.License license = new com.aspose.cells.License();
            license.setLicense(is);
            result = true;
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        // excel转化为pdf  https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/excelTest.xlsx
        excelToPdf("https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/excelTest.xlsx","C:\\Users\\lf\\Desktop\\excelToPdfTest22.pdf");

    }


}

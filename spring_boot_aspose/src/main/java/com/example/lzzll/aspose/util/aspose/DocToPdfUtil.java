package com.example.lzzll.aspose.util.aspose;

import com.aspose.words.SaveFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author lf
 * @Date 2022/11/1 9:44
 * @Description:
 */
@Slf4j
public class DocToPdfUtil {

    /**
     * 将doc文档转化为pdf文档
     * @param docUrl doc文档
     * @param pdfUrl pdf文档
     * @return
     */
    public static void docToPdf(String docUrl,String pdfUrl) {
        InputStream inputStream = null;
        try {
            // 当作一个URL来装载文件
            URL url = new URL(docUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒`
            conn.setConnectTimeout(3*1000);
            //得到输入流
            inputStream = conn.getInputStream();
            getWordLicense();
            com.aspose.words.Document document = new com.aspose.words.Document(inputStream);
            document.save(pdfUrl, SaveFormat.PDF);
        } catch (Exception e) {
            log.error("将doc文档转化为pdf文档失败:"+e.getMessage(), ExceptionUtils.getFullStackTrace(e));
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
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

        // docx格式 https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/2.docx
        docToPdf("https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/2.docx","C:\\Users\\lf\\Desktop\\ttt6.pdf");
        // doc格式 https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/docTest.doc
        docToPdf("https://qp-tiku-test.oss-cn-beijing.aliyuncs.com/docTest.doc","C:\\Users\\lf\\Desktop\\ttt7.pdf");

    }
}

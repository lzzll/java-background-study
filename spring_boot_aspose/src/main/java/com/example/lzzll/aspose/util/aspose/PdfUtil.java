/**
 * 版权所有(C) 2016 www.xiongge.club
 *
 * @author xsw
 * @date 2016-12-8 上午10:14:54
 */
package com.example.lzzll.aspose.util.aspose;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.*;

/**
 * @ClassName: HtmlToPdf
 * @Description: TODO()
 * @author xsw
 * @date 2016-12-8 上午10:14:54
 *
 */
@Slf4j
public class PdfUtil {
	//wkhtmltopdf在系统中的路径
	private static String toPdfTool = "\"D:\\Program File\\wkhtmltopdf\\bin\\wkhtmltopdf.exe\"";

	static class HtmlToPdfInterceptor extends Thread{
		private InputStream is;
		public HtmlToPdfInterceptor(InputStream is){
			this.is = is;
		}

		@Override
		public void run(){
			try{
				InputStreamReader isr = new InputStreamReader(is, "utf-8");
				BufferedReader br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line); //输出内容
				}
			}catch (IOException e){
				e.printStackTrace();
			}
		}

	}

	/**
	 * html转pdf
	 *
	 * @param srcPath  html路径，可以是硬盘上的路径，也可以是网络路径
	 * @param destPath pdf保存路径
	 * @return 转换成功返回true
	 */
	public static boolean html2pdf(String srcPath, String destPath) {
		try {
			File file = new File(destPath);
			File parent = file.getParentFile();
			// 如果pdf保存路径不存在，则创建路径
			if (!parent.exists()) {
				parent.mkdirs();
			}
			if(System.getProperty("os.name").contains("Windows")){
				srcPath = srcPath.startsWith("http") ? srcPath : "E:\\" + srcPath.replaceAll("/","\\\\");
				destPath = "E:\\" + destPath.replaceAll("/","\\\\");
			} else {
				//非windows 系统
				toPdfTool = "wkhtmltopdf";
			}
			log.info("cmd >>>> " + toPdfTool);
			StringBuilder cmd = new StringBuilder();
			cmd.append(toPdfTool);
			cmd.append(" ");
			cmd.append("--page-size A4 --dpi 300 --image-quality 100 --margin-top 0in --margin-right 0in --margin-bottom 0in --margin-left 0in --zoom 1 --no-outline --disable-smart-shrinking --page-width 210mm --page-height 297mm ");
			cmd.append(srcPath);
			cmd.append(" ");
			cmd.append(destPath);
			Process proc = Runtime.getRuntime().exec(cmd.toString());
			HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());
			HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(proc.getInputStream());
			error.start();
			output.start();
			proc.waitFor();
			if (new File(destPath).exists()) {
				return true;
			}
		} catch (Exception e) {
			log.error("html2pdf fail");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取本地临时pdf文件的页数
	 * @param tempPdfUrl
	 * @return
	 */
	public static int getPdfPages(String tempPdfUrl) throws IOException {
		PDDocument pdfDocument = null;
		try {
			if (!StringUtils.isEmpty(tempPdfUrl)){
                pdfDocument = PDDocument.load(new File(tempPdfUrl));
                return pdfDocument.getNumberOfPages();
            }
			return 0;
		} catch (IOException e) {
			log.error("获取pdf文档的页数失败，pdf文档为："+tempPdfUrl, ExceptionUtils.getFullStackTrace(e));
			return 0;
		}finally {
			if (pdfDocument != null){
				pdfDocument.close();
			}
		}
	}



	public static void main(String[] args) throws IOException {
//		PdfUtil.html2pdf("http://tiku-doc-collection-api-test.canpoint.net/answersht/v1.0/template/card/paper/preview.html", "C:\\Users\\Administrator\\Desktop\\aaqq.pdf");
//		PdfUtil.html2pdf("E:\\canpointnas\\cpcloud\\tmpfiles\\tiku\\answerSheet\\f336cb72-69ef-4987-8a16-339e53256ccd.html", "C:\\Users\\Administrator\\Desktop\\aaqq.pdf");
//		String string = String.format("<div " + 111 + ">%d.%s</br>%s%s</div>", 1, "题干", "选项", "【答案】：111<br/>【解析】：222");
//		System.out.println(string);

		int pdfPages = getPdfPages("C:\\Users\\lf\\Desktop\\pptToPdfTest2.pdf");
		System.out.println(pdfPages);
	}
}
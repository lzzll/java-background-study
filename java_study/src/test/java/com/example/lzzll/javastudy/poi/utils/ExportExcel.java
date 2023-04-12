package com.example.lzzll.javastudy.poi.utils;//package com.example.lzzll.poi.utils;
//
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.hssf.util.HSSFColor;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.List;
//
///**
// * 导出Excel文件
// *
// * @author Administrator
// *
// */
//public class ExportExcel {
//
//	/**
//	 * 文件导出方法.
//	 * @param resource List<String[]> 集合类型, 要导出的具体数据集合.
//	 * @param outputStream 输出流. 输出的excel文件保存的具体位置.
//	 * @throws IOException
//	 */
//	public void exportExcel(List<String[]> resource, OutputStream outputStream)
//			throws IOException{
//
//		// 创建一个内存Excel对象.
//		HSSFWorkbook workbook = new HSSFWorkbook();
//
//		// 创建一个表格.
//		HSSFSheet sheet = workbook.createSheet("sheet1");
//
//		// 创建表头
//		// 获取表头内容.
//		String[] headerStr = resource.get(0);
//		HSSFRow headerRow = sheet.createRow(0);
//		System.out.println(headerRow);
//		headerRow.setHeightInPoints(20);  // 设置表头行高
//
//		// 设置列宽
//		for(int i = 0; i < headerStr.length; i++){
//			sheet.setColumnWidth(i, 5000);
//		}
//
//		// 设置头单元格样式
//		HSSFCellStyle headerStyle = workbook.createCellStyle();
//		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
//		// 设置字体
//		HSSFFont headerFont = workbook.createFont();
//		headerFont.setColor(HSSFColor.VIOLET.index2);  // 设置表头颜色
//		headerFont.setFontName("宋体");
//		headerStyle.setFont(headerFont);
//
//		// 定义表头内容.
//		for(int i = 0; i < headerStr.length; i++){
//			// 创建一个单元格
//			HSSFCell headerCell = headerRow.createCell(i);
//			headerCell.setCellStyle(headerStyle);
//			headerCell.setCellValue(headerStr[i]);
//		}
//
//		// 表体内容.
//		// 样式
//		HSSFCellStyle bodyStyle = workbook.createCellStyle();
//		bodyStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
//		// 设置字体
//		HSSFFont bodyFont = workbook.createFont();
//		bodyFont.setColor(HSSFColor.BLUE.index);  // 设置表体颜色
//		bodyFont.setFontName("微软雅黑");
//		bodyStyle.setFont(bodyFont);
//
//		for(int row = 1; row < resource.size(); row++){
//			// 输出的行数据
//			String[] temp = resource.get(row);
//			// 创建行
//			HSSFRow bodyRow = sheet.createRow(row);
//			bodyRow.setHeightInPoints(15);  // 设置表体行高
//			// 循环创建列
//			for(int cell = 0; cell < temp.length; cell++){
//				HSSFCell bodyCell = bodyRow.createCell(cell);
//				bodyCell.setCellStyle(bodyStyle);
//				bodyCell.setCellValue(temp[cell]);
//			}
//		}
//
//		// 将内存创建的excel对象,输出到文件中.
//		workbook.write(outputStream);
//
//	}
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//

package com.example.lzzll.javastudy.poi;//package com.example.lzzll.poi;
//
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.junit.Test;
//
//import javax.swing.filechooser.FileSystemView;
//import java.io.*;
//import java.util.Date;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * @Author lf
// * @Date 2019/9/24 8:38
// * @Description:
// */
//public class createExcel {
//
//    // 在桌面上生成一个excel文件
//    @Test
//    public void createExcel() throws IOException {
//            // 获取桌面路径
//            FileSystemView fsv = FileSystemView.getFileSystemView();
//            String desktop = fsv.getHomeDirectory().getPath();
//            String filePath = desktop + "/template.xls";
//
//            File file = new File(filePath);
//            OutputStream outputStream = new FileOutputStream(file);
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            HSSFSheet sheet = workbook.createSheet("Sheet1");
//            HSSFRow row = sheet.createRow(0);
//            row.createCell(0).setCellValue("id");
//            row.createCell(1).setCellValue("订单号");
//            row.createCell(2).setCellValue("下单时间");
//            row.createCell(3).setCellValue("个数");
//            row.createCell(4).setCellValue("单价");
//            row.createCell(5).setCellValue("订单金额");
//            row.setHeightInPoints(30); // 设置行的高度
//
//            HSSFRow row1 = sheet.createRow(1);
//            row1.createCell(0).setCellValue("1");
//            row1.createCell(1).setCellValue("NO00001");
//
//            // 日期格式化
//            HSSFCellStyle cellStyle2 = workbook.createCellStyle();
//            HSSFCreationHelper creationHelper = workbook.getCreationHelper();
//            cellStyle2.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
//            sheet.setColumnWidth(2, 20 * 256); // 设置列的宽度
//
//            HSSFCell cell2 = row1.createCell(2);
//            cell2.setCellStyle(cellStyle2);
//            cell2.setCellValue(new Date());
//
//            row1.createCell(3).setCellValue(2);
//
//
//            // 保留两位小数
//            HSSFCellStyle cellStyle3 = workbook.createCellStyle();
//            cellStyle3.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
//            HSSFCell cell4 = row1.createCell(4);
//            cell4.setCellStyle(cellStyle3);
//            cell4.setCellValue(29.5);
//
//
//            // 货币格式化
//            HSSFCellStyle cellStyle4 = workbook.createCellStyle();
//            HSSFFont font = workbook.createFont();
//            font.setFontName("华文行楷");
//            font.setFontHeightInPoints((short) 15);
//            font.setColor(HSSFColor.RED.index);
//            cellStyle4.setFont(font);
//
//            HSSFCell cell5 = row1.createCell(5);
//            cell5.setCellFormula("D2*E2");  // 设置计算公式
//
//            // 获取计算公式的值
//            HSSFFormulaEvaluator e = new HSSFFormulaEvaluator(workbook);
//            cell5 = e.evaluateInCell(cell5);
//            System.out.println(cell5.getNumericCellValue());
//
//
//            workbook.setActiveSheet(0);
//            workbook.write(outputStream);
//            outputStream.close();
//    }
//
//    // 读取excel，解析数据
//    // 单元格中的数据是数字或者日期时，不能通过getStringCellValue()方法来获取单元格中的内容，必须通过对应数据类型的方法来获取内容
//    @Test
//    public void readExcel() throws IOException{
//        FileSystemView fsv = FileSystemView.getFileSystemView();
//        String desktop = fsv.getHomeDirectory().getPath();
//        String filePath = desktop + "/template.xls";
//
//        FileInputStream fileInputStream = new FileInputStream(filePath);
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
//        POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
//        HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
//        HSSFSheet sheet = workbook.getSheet("Sheet1");
//
//        int lastRowIndex = sheet.getLastRowNum();
//        System.out.println(lastRowIndex);
//        for (int i = 0; i <= lastRowIndex; i++) {
//            HSSFRow row = sheet.getRow(i);
//            if (row == null) { break; }
//            if (i == 0){
//                short lastCellNum = row.getLastCellNum();
//                String cellValue = null;
//                for (int j = 0; j < lastCellNum; j++) {
//                    cellValue = row.getCell(j).getStringCellValue();
//                    System.out.println(cellValue);
//                }
//            }
//            if (i>0){
//                short lastCellNum = row.getLastCellNum();
//                String cellValue = null;
//                for (int j = 0; j < lastCellNum; j++) {
//                    if (j == 2){
//                        cellValue = row.getCell(j).getDateCellValue().toString();
//                    } else if (j>2){
//                        cellValue = row.getCell(j).getNumericCellValue()+"";
//                    }else {
//                        cellValue = row.getCell(j).getStringCellValue();
//                    }
//                    System.out.println(cellValue);
//                }
//            }
//        }
//        bufferedInputStream.close();
//    }
//
//
//    /**
//     * 功能：判断字符串是否为日期格式
//     *
//     * @param strDate
//     * @return
//     */
//    public static boolean isDate(String strDate) {
//        Pattern pattern = Pattern
//                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
//        Matcher m = pattern.matcher(strDate);
//        if (m.matches()) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     * 功能：判断字符串是否为数字
//     *
//     * @param str
//     * @return
//     */
//    private static boolean isNumeric(String str) {
//        Pattern pattern = Pattern.compile("[0-9]*");
//        Matcher isNum = pattern.matcher(str);
//        if (isNum.matches()) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//
//
//}
//
//

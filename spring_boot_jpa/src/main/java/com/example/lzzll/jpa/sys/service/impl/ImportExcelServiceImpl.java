package com.example.lzzll.jpa.sys.service.impl;

import com.example.lzzll.jpa.sys.service.ImportExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author lf
 * @Date 2022/8/31 17:42
 * @Description:
 */
@Service
@Slf4j
public class ImportExcelServiceImpl implements ImportExcelService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importExcelConfig(XSSFWorkbook wb, InputStream inputStream) throws IOException {
        // 导入成功的电话
        List<String> successPhone = Lists.newArrayList();
        Workbook readBook = new XSSFWorkbook(inputStream);
        // 导出
        XSSFRow writeRow ;
        XSSFCell writeCell;
        // 导入
        Row readRow ;
        Cell readCell ;
        // 遍历每一张表
        for (int i = 0; i < readBook.getNumberOfSheets(); i++) {
            // 写入数据
            XSSFSheet writeSheet = wb.createSheet("测试");
            // 写入第一行的表头
            writeRow = writeSheet.createRow(0);
            // 第一列
            writeCell = writeRow.createCell(0);
            writeCell.setCellValue("导入电话");
            // 第二列
            writeCell = writeRow.createCell(1);
            writeCell.setCellValue("导入结果");
            Sheet readSheet = readBook.getSheetAt(i);
            // 遍历每一行,去掉第一行的表头
            for (int j = 1; j <= readSheet.getLastRowNum(); j++) {
                readRow = readSheet.getRow(j);
                readCell = readRow.getCell(0);
                double numberValue = readCell.getNumericCellValue();
//                String readPhone = String.valueOf(numberValue);
                NumberFormat nf = NumberFormat.getInstance();
                String readPhone = nf.format(numberValue);
                readPhone = readPhone.replaceAll(",","");
                System.out.println(readPhone);
//                String readPhone = readCell.getStringCellValue();
                // 写出导出数据的其它列
                writeRow = writeSheet.createRow(j);
                writeCell = writeRow.createCell(0);
                writeCell.setCellValue(readPhone);
                String regex = "^(13[0-9]|14[5-9]|15[0-35-9]|16[25-7]|17[0-8]|18[0-9]|19[0135689])[0-9]{8}$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(readPhone);
                if (matcher.find()){
                    successPhone.add(readPhone);
                    // 第二列
                    writeCell = writeRow.createCell(1);
                    writeCell.setCellValue("导入成功");
                }else {
                    // 第二列
                    writeCell = writeRow.createCell(1);
                    writeCell.setCellValue("导入失败--原因为手机号不匹配");
                }
            }
        }
        log.info("导入成功的电话:"+successPhone);
    }
}

package com.example.lzzll.jpa.sys.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream; /**
 * @Author lf
 * @Date 2022/8/31 17:42
 * @Description:
 */
public interface ImportExcelService {

    /**
     * 写入数据并导出excel
     * @param wb
     * @param inputStream
     */
    void importExcelConfig(XSSFWorkbook wb, InputStream inputStream) throws IOException;
}

package com.example.lzzll.jpa.sys.controller;

import com.example.lzzll.jpa.sys.service.ImportExcelService;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @Author lf
 * @Date 2022/8/31 17:13
 * @Description:
 */
@RestController
@RequestMapping("excel")
public class ImportExcelController {

    @Autowired
    private ImportExcelService importExcelService;


    /**
     * @api {POST} /homePageConfig/import  导入配置数据
     * @apiName import
     * @apiGroup HomeConfig
     * @apiVersion 4.0.0
     * @apiDescription lf 2022/07/12 导入首页中功能配置数据
     *
     * @apiHeader {String} CANPOINTTOKEN CANPOINTTOKEN
     * @apiParam {MultipartFile} file 导入的excel文件
     *
     * @apiSuccess (200) {String} msg 提示信息
     * @apiSuccess (200) {String} code 200 成功 非200 失败
     * @apiSuccess (200) {String} data
     *
     * @apiSuccessExample {json} 返回样例:
     * {
     *     "msg": "success",
     *     "code": 200
     * }
     */
    @RequestMapping("import")
    public void importConfig(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        if (file == null){
            throw new RuntimeException("文件不能为空");
        }
        try {
            InputStream inputStream = file.getInputStream();
            XSSFWorkbook wb = new XSSFWorkbook();
            // 写入数据并组装导出的excel
            importExcelService.importExcelConfig(wb,inputStream);
            ServletOutputStream out = response.getOutputStream();
            wb.write(out);
            wb.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new RuntimeException("导出失败");
        }
    }



}

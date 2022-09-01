package com.example.lzzll.freemarker.service.impl;

import com.example.lzzll.freemarker.entity.Permission;
import com.example.lzzll.freemarker.mappers.PermissionDao;
import com.example.lzzll.freemarker.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzzll
 * @since 2022-07-26
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements PermissionService {


    /**
     * 转换方式二 poi
     * @param args
     */
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\lf\\Desktop\\5.docx");
        XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
        PdfOptions pdfOptions = PdfOptions.create();
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\lf\\Desktop\\testChange1.pdf");
        PdfConverter.getInstance().convert(xwpfDocument,fileOutputStream,pdfOptions);
        fileInputStream.close();
        fileOutputStream.close();
    }


}

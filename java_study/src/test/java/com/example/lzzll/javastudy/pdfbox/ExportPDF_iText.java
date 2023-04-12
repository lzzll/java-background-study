package com.example.lzzll.javastudy.pdfbox;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;


public class ExportPDF_iText {

    public static void main(String[] args) throws Exception {

        FileOutputStream fos = new FileOutputStream("C:\\Users\\lf\\Desktop\\test\\table5.pdf");
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, fos);
        writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage);
        document.setPageSize(PageSize.A4);
        document.open();

        float[] widths = {240, 240, 300};
        PdfPTable table = new PdfPTable(widths);
        table.setLockedWidth(true);
        table.setTotalWidth(780);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        Object[][] datas = {{"区域产品销售额"},{"区域", "总销售额(万元)", "总利润(万元)简单的表格"}, {"江苏省" , 9045,  2256}, {"广东省", 3000, 690}};
        for(int i = 0; i < datas.length; i++) {
            for(int j = 0; j < datas[i].length; j++) {
                PdfPCell pdfCell = new PdfPCell();
                pdfCell.setMinimumHeight(30);

                //设置单元格样式
                pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                pdfCell.setBackgroundColor(new BaseColor(0xdd7e6b));

                pdfCell.setBorder(0);
//                pdfCell.setBorderWidthTop(0.1f);
                pdfCell.setBorderWidthBottom(0.1f);
//                pdfCell.setBorderWidthLeft(0.1f);
                pdfCell.setBorderWidthRight(0.1f);
//                pdfCell.setBorderColorBottom(new BaseColor(0x674ea7));
                pdfCell.setBorderColorLeft(new BaseColor(0x674ea7));
//                pdfCell.setBorderColorRight(new BaseColor(0x674ea7));
                pdfCell.setBorderColorTop(new BaseColor(0x674ea7));

                //设置单元格文本字体样式
                Font font = getPdfChineseFont();
                if(i == datas.length - 1 && j == 3 - 1) {
                    font.setColor(new BaseColor(0xff0000));
                    font.setSize(16);
                    font.setStyle("bold");
                    font.setStyle("italic");
                    font.setStyle("underline");
                }

                //合并单元格
                if(i == 0 && j == 0) {
                    pdfCell.setRowspan(1);
                    pdfCell.setColspan(3);
                }

                Paragraph paragraph = new Paragraph(String.valueOf(datas[i][j]), font);
                pdfCell.setPhrase(paragraph);

                table.addCell(pdfCell);
            }
        }

        //单元格插入图片
        byte[] bt = FileUtils.readFileToByteArray(new File("C:\\Users\\lf\\Desktop\\test\\批次流程图.png"));
        PdfPCell pdfCell = new PdfPCell();
        pdfCell.setImage(Image.getInstance(bt));
        table.addCell(pdfCell);

        //插入子表格
        pdfCell = new PdfPCell();
        pdfCell.setRowspan(1);
        pdfCell.setColspan(2);

//        PdfPTable suTtable = new PdfPTable(new float[]{100, 100});
//        PdfPCell subPdfCell = new PdfPCell();
//        subPdfCell.setPhrase(new Paragraph("sub1", getPdfChineseFont()));
//        suTtable.addCell(subPdfCell);
//        subPdfCell = new PdfPCell();
//        subPdfCell.setPhrase(new Paragraph("sub2", getPdfChineseFont()));
//        suTtable.addCell(subPdfCell);
//
//        pdfCell.addElement(suTtable);
        table.addCell(pdfCell);

        document.add(table);

        //文档插入绝对位置图片
//        Image image = Image.getInstance(bt);
//        int x = 30;
//        int y = 230;
//        image.setAbsolutePosition(x + document.leftMargin(),  PageSize.A4.getHeight() - y -
//                image.getHeight() - document.topMargin());
//        document.add(image);

        document.close();
    }


    public static Font getPdfChineseFont() throws Exception {
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
                BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese, 12, Font.NORMAL);
        return fontChinese;
    }
}
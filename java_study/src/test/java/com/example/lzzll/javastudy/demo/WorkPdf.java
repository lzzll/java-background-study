package com.example.lzzll.javastudy.demo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkPdf {
    Document document=new Document();
    public WorkPdf(File file){
        document.setPageSize(PageSize.A4);//设置为A4大小
        try {
            PdfWriter pdfWriter=PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            pdfWriter.open();
            //弹出打印按钮
            pdfWriter.addJavaScript("this.print({bUI:true,bSilent:false,bShrinkToFit:true});", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 打印pdf
     * @param
     */
    public static void pdfAction(HttpServletRequest request,HttpServletResponse response){
//        HttpServletRequest request=action.getRequest();
//        HttpServletResponse response=action.getResponse();
//        UserEntity userEntity=action.getUser();
//        String id=request.getParameter("id");
//        //查询数据
//        AData order=DataProxy.Get("order", new AValue(id), userEntity);
//        if(order!=null){

            //文件名
            String fileName="打印"+".pdf";
            //路径 部署目录下的temp/pdf
            String pathStr=request.getSession().getServletContext().getRealPath("\\")+"\\temp\\pdf\\"+fileName;
            File file=new File(pathStr);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            WorkPdf tPdf=new WorkPdf(file);
            //调用具体的步骤(到底根据什么来判断调用具体的打印，后期在整合)
            //tPdf.printTaxBill(request, response, order);
            tPdf.printBillLading(request,response);

            try {
                PrintWriter pwWriter=response.getWriter();
                pwWriter.print("<iframe id='ifr' name='ifr' src='/temp/pdf/" + fileName
                        + "' width='100%' height='100%'></iframe>");
                pwWriter.flush();
                pwWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }
    }
    /**
     *
     * @param req
     * @param res
     * @param
     */
    private void printTaxBill(HttpServletRequest req,HttpServletResponse res) {

        try {
            //设置中文
            String path=req.getSession().getServletContext().getRealPath("")+ "\\cloud\\font\\MSYH.TTF";
            BaseFont bfOne = BaseFont.createFont(path, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            BaseFont bfTwo = BaseFont.createFont(path, BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
            //设置字体
            Font font1 = new Font(bfOne,8);//右上角字体
            Font font2 = new Font(bfOne,15);//左上角字体
            Font font3 = new Font(bfTwo,17);//左上角字体(粗体)
            Font font4 = new Font(bfOne,8);//表格里的字体
            //右上角小标题
            Paragraph paragraph1  = new Paragraph();
            paragraph1.setFont(font1);
            paragraph1.setAlignment(Element.ALIGN_RIGHT);
            String littleTStr="Form Approved OMB No. "+"xxnn-oouu";
            paragraph1.add(littleTStr);
            paragraph1.setSpacingAfter(1);
            //时间
            SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
            String date=sdf.format(new Date());
            PdfPTable paraTable=new PdfPTable(1);
            paraTable.setWidthPercentage(100f);
            PdfPCell pdfPCell=new PdfPCell(new Paragraph("EXP."+date,font1));
            pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            pdfPCell.setBorder(0);
            paraTable.addCell(pdfPCell);
            paraTable.setSpacingAfter(3);
            //16列表格  第一行
            PdfPTable table=new PdfPTable(16);
            table.setWidthPercentage(100f);
            String leftTitletwoString="U.S. Customs and Border Protection";
            String leftTitleStringOne="DEPARTMENT OF HOMELAND SECURITY "+leftTitletwoString;
            Paragraph litleParagraph=new Paragraph(leftTitleStringOne);
            litleParagraph.setFont(font2);
            PdfPCell leftTitleOne=new PdfPCell(litleParagraph);
            leftTitleOne.setColspan(8);
            leftTitleOne.setBorder(0);
            leftTitleOne.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(leftTitleOne);
            PdfPCell content1=new PdfPCell(new Paragraph("1.Filer Code/Entry No."+"\n"+"content1",font4));
            content1.setColspan(3);
            content1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content1);
            PdfPCell content2=new PdfPCell(new Paragraph("2.Entry Type"+"\n"+"content1",font4));
            content2.setHorizontalAlignment(Element.ALIGN_CENTER);
            content2.setColspan(2);
            table.addCell(content2);
            PdfPCell content3=new PdfPCell(new Paragraph("3.Summary Date"+"\n"+"content1",font4));
            content3.setHorizontalAlignment(Element.ALIGN_CENTER);
            content3.setColspan(3);
            table.addCell(content3);

            //左边标题三  第二行
            String leftTitleThreeStr="ENTRY SUMMARY";
            PdfPCell leftTitleTwo=new PdfPCell(new Paragraph(leftTitleThreeStr,font3));
            leftTitleTwo.setColspan(8);
            leftTitleTwo.setHorizontalAlignment(Element.ALIGN_CENTER);
            leftTitleTwo.setBorder(0);
            table.addCell(leftTitleTwo);

            PdfPCell content4=new PdfPCell(new Paragraph("4.Surely No."+"\n"+"content1",font4));
            content4.setColspan(2);
            content4.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content4);

            PdfPCell content5=new PdfPCell(new Paragraph("Bond Type"+"\n"+"content1",font4));
            content5.setHorizontalAlignment(Element.ALIGN_CENTER);
            content5.setColspan(2);
            table.addCell(content5);

            PdfPCell content6=new PdfPCell(new Paragraph("6.Port Code"+"\n"+"content1",font4));
            content6.setColspan(2);
            content6.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content6);

            PdfPCell content7=new PdfPCell(new Paragraph("7.Entry Date"+"\n"+"content1",font4));
            content7.setColspan(2);
            content7.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content7);
            //第三行 第一列
            PdfPCell content8=new PdfPCell(new Paragraph("8.Importing Carrier"+"\n"+"content1",font4));
            content8.setColspan(4);
            content8.setHorizontalAlignment(Element.ALIGN_CENTER);
            content8.setPaddingRight(4);
            table.addCell(content8);
            //第二列
            PdfPCell content9=new PdfPCell(new Paragraph("9.Mode of Transport"+"\n"+"content1",font4));
            content9.setColspan(4);
            content9.setHorizontalAlignment(Element.ALIGN_CENTER);			table.addCell(content9);
            //第三列
            PdfPCell content10=new PdfPCell(new Paragraph("10.Country of Origin"+"\n"+"CN",font4));
            content10.setColspan(5);
            content10.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content10);
            //第四列
            PdfPCell content11=new PdfPCell(new Paragraph("11.Import Date"+"\n"+"content1",font4));
            content11.setColspan(3);
            content11.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content11);

            //第四行 第一列
            PdfPCell content12=new PdfPCell(new Paragraph("12.B/L or AWB No"+"\n"+"content1",font4));
            content12.setColspan(4);
            content12.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content12);
            //第二列
            PdfPCell content13=new PdfPCell(new Paragraph("13.Manufacturer ID"+"\n"+"content1",font4));
            content13.setColspan(4);
            content13.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content13);
            //第三列
            PdfPCell content14=new PdfPCell(new Paragraph("14.Exporting Country"+"\n"+"CN",font4));
            content14.setColspan(5);
            content14.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content14);
            //第四列
            PdfPCell content15=new PdfPCell(new Paragraph("15.Export Date"+"\n"+"content1",font4));
            content15.setColspan(3);
            content15.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content15);

            //第五行 第一列
            PdfPCell content16=new PdfPCell(new Paragraph("16.I.T.NO"+"\n"+"content1",font4));
            content16.setColspan(3);
            content16.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content16);

            PdfPCell content17=new PdfPCell(new Paragraph("17.I.T.Date"+"\n"+"content1",font4));
            content17.setColspan(2);
            content17.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content17);
            //第三，四列
            PdfPCell content18=new PdfPCell(new Paragraph("18.Missing Docs"+"\n"+"content1",font4));
            content18.setColspan(3);
            content18.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content18);
            //第五六列
            PdfPCell content19=new PdfPCell(new Paragraph("19.Foreign Port of Lading"+"\n"+"2704",font4));
            content19.setColspan(4);
            content19.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content19);
            //第七八列
            PdfPCell content20=new PdfPCell(new Paragraph("20.U.S.Port of Unlading"+"\n"+"content1",font4));
            content20.setColspan(4);
            content20.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content20);

            //第六行  第一二列
            PdfPCell content21=new PdfPCell(new Paragraph("21.Location of Goods/G.O.No"+"\n"+"content1",font4));
            content21.setColspan(4);
            content21.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content21);
            //第三四列
            PdfPCell content22=new PdfPCell(new Paragraph("22.Consignee No."+"\n"+"content1",font4));
            content22.setColspan(4);
            content22.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content22);
            //第五六列
            PdfPCell content23=new PdfPCell(new Paragraph("23.Importer No."+"\n"+"content1",font4));
            content23.setColspan(4);
            content23.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content23);
            //第七八列
            PdfPCell content24=new PdfPCell(new Paragraph("24.Reference No."+"\n"+"content1",font4));
            content24.setColspan(4);
            content24.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content24);

            //第七行 第一列
            String contentStr25="25.Ultimage Consignee Name and Adress";
            PdfPCell content25=new PdfPCell(new Paragraph(contentStr25,font4));
            content25.setColspan(8);
            content25.setBorderWidthBottom(0);
            content25.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(content25);
            //第二列
            String contentStr26="26.Importer of Record Name and Adress";
            PdfPCell content26=new PdfPCell(new Paragraph(contentStr26+"\n"+"content1",font4));
            content26.setColspan(8);
            content26.setBorderWidthBottom(0);
            content26.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(content26);

            //第七_1行 第一列
            String contentStr25_1="City                                        State                               Zip       ";
            PdfPCell content25_1=new PdfPCell(new Paragraph(contentStr25_1,font4));
            content25_1.setColspan(8);
            content25_1.setBorderWidthTop(0);
            content25_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(content25_1);
            //第二列
            PdfPCell content26_1=new PdfPCell(new Paragraph(contentStr25_1,font4));
            content26_1.setColspan(8);
            content26_1.setBorderWidthTop(0);
            content26_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(content26_1);

            //第八，
            PdfPCell content27=new PdfPCell(new Paragraph("27",font4));
            content27.setColspan(1);
            content27.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content27);

            PdfPCell content28=new PdfPCell(new Paragraph("28.Description of Merchandise",font4));
            content28.setColspan(7);
            content28.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content28);

            PdfPCell content29=new PdfPCell(new Paragraph("32.",font4));
            content29.setColspan(2);
            content29.setHorizontalAlignment(Element.ALIGN_LEFT);
            content29.setBorderWidthBottom(0);
            table.addCell(content29);

            PdfPCell content30=new PdfPCell(new Paragraph("33.",font4));
            content30.setColspan(3);
            content30.setHorizontalAlignment(Element.ALIGN_CENTER);
            content30.setBorderWidthBottom(0);
            table.addCell(content30);

            PdfPCell content31=new PdfPCell(new Paragraph("34.",font4));
            content31.setColspan(3);
            content31.setHorizontalAlignment(Element.ALIGN_CENTER);
            content31.setBorderWidthBottom(0);
            table.addCell(content31);

            //第8_1行
            PdfPCell content27_1=new PdfPCell(new Paragraph("Line No.",font4));
            content27_1.setColspan(1);
            content27_1.setRowspan(2);
            content27_1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content27_1);

            PdfPCell content29_1=new PdfPCell(new Paragraph("A.HTSUS No."+"\n"+"B.AD/CVD Case No."+"\n",font4));
            content29_1.setColspan(3);
            content29_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            content29_1.setRowspan(2);
            table.addCell(content29_1);

            PdfPCell content30_1=new PdfPCell(new Paragraph("A.Gross Weight"+"\n"+"B.Manifist Qty.",font4));
            content30_1.setColspan(2);
            content30_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //content29.setBorderWidthBottom(0);
            content30_1.setRowspan(2);
            table.addCell(content30_1);

            PdfPCell content31_1=new PdfPCell(new Paragraph("Net Quantity in HTSUS Units",font4));
            content31_1.setColspan(2);
            content31_1.setHorizontalAlignment(Element.ALIGN_CENTER);
            //content30.setBorderWidthBottom(0);
            content31_1.setRowspan(2);
            table.addCell(content31_1);

            PdfPCell content32_1=new PdfPCell(new Paragraph("A.Entered Value"+"\n"+"B.CHGS"+"\n"+"C.Relationship",font4));
            content32_1.setColspan(2);
            content32_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            content32_1.setBorderWidthTop(0);
            content32_1.setRowspan(2);
            table.addCell(content32_1);

            PdfPCell content33_1=new PdfPCell(new Paragraph("A.HTSUS Rate"+"\n"+"B.AD/CVD Rate"+"\n"+"C.IRC Rate"+"\n"+"D.Visa No.",font4));
            content33_1.setColspan(3);
            content33_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            content33_1.setBorderWidthTop(0);
            content33_1.setRowspan(2);
            table.addCell(content33_1);

            PdfPCell content34_1=new PdfPCell(new Paragraph("Duty and I.R. Tax",font4));
            content34_1.setColspan(3);
            content34_1.setHorizontalAlignment(Element.ALIGN_CENTER);
            content34_1.setBorderWidthTop(0);
            table.addCell(content34_1);

            PdfPCell content34_2=new PdfPCell(new Paragraph("Dollars                Cents",font4));
            content34_2.setColspan(3);
            content34_2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content34_2);
            //暂时空白处理--应该是用for处理
            PdfPCell space001=new PdfPCell(new Paragraph("\n"+"001"+"\n",font4));
            space001.setColspan(1);
            space001.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(space001);

            PdfPCell space002=new PdfPCell(new Paragraph("暂时空白",font4));
            space002.setColspan(7);
            space002.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(space002);

            PdfPCell space003=new PdfPCell(new Paragraph("暂时空白",font4));
            space003.setColspan(2);
            space003.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(space003);

            PdfPCell space004=new PdfPCell(new Paragraph("暂时空白",font4));
            space004.setColspan(3);
            space004.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(space004);

            PdfPCell space005=new PdfPCell(new Paragraph("暂时空白",font4));
            space005.setColspan(3);
            space005.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(space005);

            //CBP模块
            //第一行
            PdfPCell content39_1=new PdfPCell(new Paragraph("Other fee Summary for block 39"+"\n",font4));
            content39_1.setColspan(4);
            content39_1.setBorderWidthBottom(0);
            content39_1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content39_1);

            PdfPCell content35=new PdfPCell(new Paragraph("35.Total Entered Value"+"\n",font4));
            content35.setColspan(4);
            content35.setBorderWidthBottom(0);
            content35.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(content35);

            PdfPCell contentCBP=new PdfPCell(new Paragraph("CBP USE ONLY",font4));
            contentCBP.setColspan(5);
            contentCBP.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(contentCBP);

            PdfPCell contentTOTALS=new PdfPCell(new Paragraph("TOTALS",font4));
            contentTOTALS.setColspan(3);
            contentTOTALS.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(contentTOTALS);
            //
            //第二行
            PdfPCell contentSpace1=new PdfPCell(new Paragraph("                    "+"\n",font4));
            contentSpace1.setColspan(4);
            contentSpace1.setBorderWidthTop(0);
            contentSpace1.setBorderWidthBottom(0);
            contentSpace1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(contentSpace1);

            PdfPCell content$=new PdfPCell(new Paragraph("\n"+"$",font4));
            content$.setColspan(4);
            content$.setBorderWidthTop(0);
            content$.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(content$);

            PdfPCell contentCBPA=new PdfPCell(new Paragraph("A.LIQ CODE"+"\n"+"    ",font4));
            contentCBPA.setColspan(2);
            contentCBPA.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(contentCBPA);

            PdfPCell contentCBPB=new PdfPCell(new Paragraph("B.Ascertained Duty"+"\n"+"    "+"\n",font4));
            contentCBPB.setColspan(3);
            contentCBPB.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(contentCBPB);

            PdfPCell content37=new PdfPCell(new Paragraph("37.Duty"+"\n"+"    "+"\n",font4));
            content37.setColspan(3);
            content37.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(content37);
            //CBP模块
            //第三行
            table.addCell(contentSpace1);

            PdfPCell contentTotalFee=new PdfPCell(new Paragraph("Total Other Fees"+"\n",font4));
            contentTotalFee.setColspan(4);
            contentTotalFee.setBorderWidthBottom(0);
            contentTotalFee.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(contentTotalFee);

            PdfPCell contentReason=new PdfPCell(new Paragraph("\n"+"REASON CODE"+"\n",font4));
            contentReason.setColspan(2);
            contentReason.setHorizontalAlignment(Element.ALIGN_LEFT);
            contentReason.setBorderWidthBottom(0);
            table.addCell(contentReason);

            PdfPCell contentBCPC=new PdfPCell(new Paragraph("C.Ascertained Tax"+"\n",font4));
            contentBCPC.setColspan(3);
            contentBCPC.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(contentBCPC);

            PdfPCell content38=new PdfPCell(new Paragraph("38.Tax"+"\n",font4));
            content38.setColspan(3);
            content38.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(content38);
            //CBP模块
            //第四行
            table.addCell(contentSpace1);
            table.addCell(content$);
            PdfPCell contentSpace2=new PdfPCell(new Paragraph("                    "+"\n",font4));
            contentSpace2.setColspan(2);
            contentSpace2.setBorderWidthTop(0);
            contentSpace2.setBorderWidthBottom(0);
            contentSpace2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(contentSpace2);
            PdfPCell contentBCPD=new PdfPCell(new Paragraph("D.Ascertained Other"+"\n",font4));
            contentBCPD.setColspan(3);
            contentBCPD.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(contentBCPD);

            PdfPCell content39=new PdfPCell(new Paragraph("39.Other"+"\n",font4));
            content39.setColspan(3);
            content39.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(content39);
            //CBP模块
            //第四行
            String contentStr36="36.DECLARATION OF IMPORTER OF RECORD"+"\n"+"(OWNER OR PURCHASER) OR AUTHORIZED AGENT";
            PdfPCell content36=new PdfPCell(new Paragraph(contentStr36,font4));
            content36.setColspan(8);
            content36.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(content36);
            PdfPCell contentSpace3=new PdfPCell(new Paragraph("                    "+"\n",font4));
            contentSpace3.setColspan(2);
            contentSpace3.setBorderWidthTop(0);
            contentSpace3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(contentSpace3);
            PdfPCell contentBCPE=new PdfPCell(new Paragraph("E.Ascertained Total"+"\n",font4));
            contentBCPE.setColspan(3);
            contentBCPE.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(contentBCPE);
            PdfPCell content40=new PdfPCell(new Paragraph("40.Total"+"\n",font4));
            content40.setColspan(3);
            content40.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(content40);
            String textLineOne="   rrrrr";
            PdfPCell contentText=new PdfPCell(new Paragraph(textLineOne,font4));
            contentText.setColspan(16);
            contentText.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(contentText);
            String endingText="41.DECLARE NAME";
            PdfPCell contentEnding1=new PdfPCell(new Paragraph(endingText,font4));
            contentEnding1.setColspan(4);
            contentEnding1.setBorderWidthRight(0);
            contentEnding1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(contentEnding1);

            PdfPCell contentEnding2=new PdfPCell(new Paragraph("TITLE",font4));
            contentEnding2.setColspan(4);
            contentEnding2.setBorderWidthLeft(0);
            contentEnding2.setBorderWidthRight(0);
            contentEnding2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(contentEnding2);

            PdfPCell contentEnding3=new PdfPCell(new Paragraph("SIGNATURE",font4));
            contentEnding3.setColspan(4);
            contentEnding3.setBorderWidthLeft(0);
            contentEnding3.setBorderWidthRight(0);
            contentEnding3.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(contentEnding3);

            PdfPCell contentEnding4=new PdfPCell(new Paragraph("DATE",font4));
            contentEnding4.setColspan(4);
            contentEnding4.setBorderWidthLeft(0);
            contentEnding4.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(contentEnding4);
            document.open();
            document.add(paragraph1);
            document.add(paraTable);
            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
        document.close();
    }

    /**
     *
     * @param req
     * @param res
     * @param
     */
    private void printBillLading(HttpServletRequest req,HttpServletResponse res){
        String path=req.getSession().getServletContext().getRealPath("")+ "\\cloud\\font\\MSYH.TTF";
        try {
            BaseFont bfOne = BaseFont.createFont(path, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            BaseFont bfTwo = BaseFont.createFont(path, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Font font1 = new Font(bfOne,8);//表格字体
            Font font2 = new Font(bfOne,6);//右边文档
            Font font3 = new Font(bfTwo,13);
            //12列表格
            PdfPTable table=new PdfPTable(12);
            table.setWidthPercentage(100f);
            //左边第一行
            String contentStr1="1.Shipper";
            PdfPCell content1=new PdfPCell(new Paragraph(contentStr1,font1));
            content1.setRowspan(2);
            content1.setColspan(7);
            table.addCell(content1);

            table.addCell("   ");
            PdfPCell contentRight1=new PdfPCell(new Paragraph("BL/NO."+"\n"+"149704923351提单号"));
            contentRight1.setColspan(3);
            contentRight1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(contentRight1);
            table.addCell("   ");
            PdfPCell space=new PdfPCell(new Paragraph("\n"));//目前是空格
            space.setColspan(5);
            table.addCell(space);

            //左边第二行
            String contentStr2="2.Consignee";
            PdfPCell content2=new PdfPCell(new Paragraph(contentStr2+"\n"+"\n",font1));
            content2.setColspan(7);
            table.addCell(content2);

            //右边2，3行
            String contentRightStr2="BILL OF LADING";
            String contenText="RECEIVED inexternal apparent good order and condition except as otherwise noted.The total number"+
                    "of packages or units in the container,the description of the goods and the weight shown in this BILL of lading are"+
                    "fumished by the Marchants,and which the carrier has no reasonable means of checking and is not a part of this Bill"+
                    "of BILL of Lading as if each had personally signed this BILL of Lading."+"\n"+"(Terms continued on the back hereof,"+
                    "please read carefully)"+"\n"+"Appllcable Only When Document Used as a Combined Transport Bill OF LADING";
            PdfPCell contentRight2=new PdfPCell(new Paragraph(contentRightStr2,font1));
            contentRight2.setColspan(5);
            table.addCell(contentRight2);

            //左边第三行
            String contentStr3="3.Notify Party";
            PdfPCell content3=new PdfPCell(new Paragraph(contentStr3+"\n"+"\n"+"SAME  AS  CONSIGNEE",font1));
            content3.setColspan(7);
            table.addCell(content3);
            //右边第三行
            PdfPCell contentRight3=new PdfPCell(new Paragraph(contenText,font2));
            contentRight3.setColspan(5);
            table.addCell(contentRight3);


            //左边第4行
            String contentStr4="4.Combined Transport Pre-carriage by";
            PdfPCell content4=new PdfPCell(new Paragraph(contentStr4+"\n",font1));
            content4.setColspan(3);
            table.addCell(content4);

            String contentStr5="5.Combined Transport Place of Receipt/Date";
            PdfPCell content5=new PdfPCell(new Paragraph(contentStr5+"\n"+"YANTIAN  起运港",font1));
            content5.setColspan(4);
            table.addCell(content5);

            String contentRightStr4="For delivery of goods please apply to :";
            PdfPCell contentRight4=new PdfPCell(new Paragraph(contentRightStr4,font1));
            contentRight4.setColspan(5);
            contentRight4.setBorderWidthBottom(0);
            table.addCell(contentRight4);

            //左边第五行
            String contentStr6="6.Ocean Vessel Voy.No."+"\n"+"EVER EXCEL 1015-127E航次";
            PdfPCell content6=new PdfPCell(new Paragraph(contentStr6,font1));
            content6.setColspan(3);
            table.addCell(content6);

            String contentStr7="7.Port of loading"+"\n"+"YANTIAN,CHINA起运港";
            PdfPCell content7=new PdfPCell(new Paragraph(contentStr7,font1));
            content7.setColspan(4);
            table.addCell(content7);

            //右边5，6行
            String contentRightStr5="  "+"\n"+"\n";
            PdfPCell contentRight5=new PdfPCell(new Paragraph(contentRightStr5,font1));
            contentRight5.setColspan(5);
            contentRight5.setBorderWidthTop(0);
            contentRight5.setRowspan(2);
            table.addCell(contentRight5);

            //左边第6行
            String contentStr8="8.Port of Discharge"+"\n"+"LOS ANGELES,CA目的地港";
            PdfPCell content8=new PdfPCell(new Paragraph(contentStr8,font1));
            content8.setColspan(3);
            table.addCell(content8);

            String contentStr9="9.Place of Delivery"+"\n"+"LOS ANGELES,CA目的地港";
            PdfPCell content9=new PdfPCell(new Paragraph(contentStr9,font1));
            content9.setColspan(4);
            table.addCell(content9);
            //第七行
            String contentStr10="Marks& Nos.Container / Seal No.";
            table.addCell(getGoodsInfo(contentStr10, 3, 1, font1,Element.ALIGN_LEFT));
            String contentStr11="NO.of Containers or Packages";
            table.addCell(getGoodsInfo(contentStr11, 2, 1, font1,Element.ALIGN_LEFT));
            String contentStr12="Description of Goods (if Dangerous Goods,See Clause 12)";
            table.addCell(getGoodsInfo(contentStr12, 3, 1, font1,Element.ALIGN_LEFT));
            String contentStr13="Gross Weight Kgs";
            table.addCell(getGoodsInfo(contentStr13, 2, 1, font1,Element.ALIGN_LEFT));
            String contentStr14="Measurement";
            table.addCell(getGoodsInfo(contentStr14, 2, 1, font1,Element.ALIGN_LEFT));
            //货物内容
            table.addCell(getGoodsInfo("content2", 3, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("content2", 2, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("content2", 3, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("content2", 2, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("content2", 2, 1, font1,Element.ALIGN_LEFT));
            //各种number
            table.addCell(getGoodsInfo("BG NO", 1, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("CTN NO", 1, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("STYLE NO", 2, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("ITEM", 2, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("COLOR", 1, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("QTY", 1, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("CNTR NO", 1, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("REMARKS", 4, 1, font1,Element.ALIGN_LEFT));
            //number 内容
            table.addCell(getGoodsInfo("xx-xx", 1, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("xx-xx", 1, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("xx-xx", 2, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("xx-xx", 2, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("xx-xx", 1, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("xx-xx", 1, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("xx-xx", 1, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("xx-xx", 4, 1, font1,Element.ALIGN_LEFT));
            //下面表格 第一行
            String content10="10.Total Number of containers and / or packages(in words)Subject to Clause 4 Limitation";
            PdfPCell cell10=getGoodsInfo(content10, 5, 1, font1, Element.ALIGN_LEFT);
            cell10.setBorderWidthRight(0);
            table.addCell(cell10);

            String contentStr10_1="SAY TOTAL:ONE FORTY FT.HQ CONTAINER ONLY.";
            PdfPCell cell10_1=getGoodsInfo(contentStr10_1,7, 1, font1, Element.ALIGN_CENTER);
            cell10_1.setBorderWidthLeft(0);
            table.addCell(cell10_1);
            //第二行 标题
            table.addCell(getGoodsInfo("11.Freight & Charges Deciared Value Charge", 3, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("Revenue Tons", 2, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("Rate", 1, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("Per", 2, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("Prepaid", 2, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("Collect", 2, 1, font1,Element.ALIGN_LEFT));
            //内容
            table.addCell(getGoodsInfo("conten2", 3, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("conten2", 2, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("conten2", 1, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("conten2", 2, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("conten2", 2, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("conten2", 2, 1, font1,Element.ALIGN_LEFT));
            //倒数第二块
            table.addCell(getGoodsInfo("Ex.Rate", 2, 2, font1,Element.ALIGN_LEFT));

            table.addCell(getGoodsInfo("Prepaid at "+"\n   ", 2, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("Payable at "+"\n", 3, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("Place of B(s)L Issue/Date"+"\n   ", 5, 1, font1,Element.ALIGN_LEFT));

            table.addCell(getGoodsInfo("Total Prepaid"+"\n", 2, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("Number of Original B(s)/L"+"\n   ", 3, 1, font1,Element.ALIGN_LEFT));
            table.addCell(getGoodsInfo("Signed for the Carrier. "+"\n   ", 5, 1, font1,Element.ALIGN_LEFT));
            //日期及其他
            PdfPCell cellend=getGoodsInfo("LADEN ON BOARD THE VESSEL", 12, 1, font3, Element.ALIGN_LEFT);
            cellend.setBorder(0);
            PdfPCell cellend2=getGoodsInfo("Date                       by", 12, 1, font3, Element.ALIGN_LEFT);
            cellend2.setBorder(0);
            table.addCell(cellend);
            table.addCell(cellend2);
            document.open();
            document.add(table);

        } catch (Exception e) {
            e.printStackTrace();
        }
        document.close();
    }

    /**
     * 没有对参数进行判断，调用注意)
     * @param info
     * @param cos
     * @param rows
     * @param font
     * @return
     */
    private PdfPCell getGoodsInfo(String info,int cos,int rows,Font font,int element){
        PdfPCell cell=new PdfPCell(new Paragraph(info,font));
        cell.setRowspan(rows);
        cell.setColspan(cos);
        cell.setHorizontalAlignment(element);
        return cell;
    }
}

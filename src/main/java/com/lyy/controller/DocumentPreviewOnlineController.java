package com.lyy.controller;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 *
 * Created by liyueyang on 2019/6/13.
 */
@RestController
@Description("文档(word、pdf)在线预览")
public class DocumentPreviewOnlineController {
    @Value("${PDF_LOCAL_PATH}")
    private String pdfLocalPath;
    @Value("${OPEN_OFFICE_HOST}")
    private String openOfficeHost;
    @Value("${OPEN_OFFICE_PORT}")
    private int openOfficePort;

    /**
     * 读取本地pdf,这里设置的是预览
     */
    @RequestMapping(value = "localPdfWatch")
    public void localPdfWatch(HttpServletResponse response,String pdfFile) {
        response.reset();
        response.setContentType("application/pdf");
        try {
            File path = new File(pdfFile);
            FileInputStream fileInputStream = new FileInputStream(path);
            OutputStream outputStream = response.getOutputStream();
            IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);
            // 强制浏览器下载
            //response.setHeader("content-disposition", "attachment;filename=" + pdfLocalPath);
            // 浏览器尝试打开,支持office online或浏览器预览pdf功能
            response.setHeader("Content-Disposition","inline; filename = file");
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * word2pdf ---> pdf在线预览
     */
    @RequestMapping(value = "word2PdfThenWatch")
    public void word2pdfThenWatch(HttpServletResponse response,String filePath) throws Exception {
        String fileNameWithPath = filePath.substring(0, filePath.lastIndexOf("."));
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("."));
        File docFile = new File(filePath);
        String pdfFilePath = pdfLocalPath.trim() == "" || pdfLocalPath.trim().equals("") ?
                fileNameWithPath + ".pdf" : pdfLocalPath + "\\" + fileName + ".pdf";
        File pdfFile = new File(pdfFilePath);
        doc2pdf(docFile,pdfFile);
        localPdfWatch(response,pdfFilePath);
    }

    private void doc2pdf(File docFile,File pdfFile) throws Exception {
        if (docFile.exists()) {
            if (!pdfFile.exists()) {
                OpenOfficeConnection connection = new SocketOpenOfficeConnection(openOfficeHost, openOfficePort);
                try {
                    connection.connect();
                    DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
                    converter.convert(docFile, pdfFile);
                    connection.disconnect();
                    System.out.println("**pdf转换成功，PDF输出：" + pdfFile.getPath() + "**");
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            } else {
                System.out.println("**已经转换为pdf，不需要再进行转化**");
            }
        } else {
            System.out.println("**需要转换的文档不存在，无法转换**");
        }
    }

}

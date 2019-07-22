package com.lyy.controller;

import com.lyy.pojo.FileInfo;
import com.lyy.utils.DownloadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Description;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by liyueyang on 2019/6/14.
 */
@RestController
@Description("文件的上传下载")
@Api(tags = "文件上传下载模块")
@RequestMapping(value = "uploadAndDownLoad")
public class UploadAndDownloadController {
    @Value("${UPLOAD_PATH}")
    private String uploadPath;

    /**
     * 文件上传 MultipartFile
     */
    @RequestMapping(value = "upload")
    @ApiOperation("文件上传")
    public FileInfo upload(@ApiParam(value = "上传的文件", required = true) MultipartFile multipartFile) throws IOException {
        /**
         * 这里是写到本地
         * 还可以用file.getInputStrem()
         */
        File localFile = new File(uploadPath, multipartFile.getName());
        // 把传入的文件写到本地文件
        multipartFile.transferTo(localFile);
        System.out.println("文件名称：" + multipartFile.getName());
        System.out.println("文件大小：" + multipartFile.getSize());
        // getAbsolutePath为绝对路径
        return new FileInfo(localFile.getAbsolutePath());
    }

    /**
     * 文件上传 file
     */
    @RequestMapping(value = "upload2")
    public FileInfo upload2(String filePath) throws IOException {
        MultipartFile multipartFile = fileToMultipartFile(filePath);
        /**
         * 这里是写到本地
         * 还可以用file.getInputStrem()
         */
        File localFile = new File(uploadPath, multipartFile.getName());
        // 把传入的文件写到本地文件
        multipartFile.transferTo(localFile);
        System.out.println("文件名称：" + multipartFile.getName());
        System.out.println("文件大小：" + multipartFile.getSize());
        // getAbsolutePath为绝对路径
        return new FileInfo(localFile.getAbsolutePath());
    }

    /**
     * 下载 --> 文件在服务器上的路径，包含文件名
     */
    @RequestMapping(value = "download")
    public void download(HttpServletResponse response, String filePath){
        DownloadUtils.download(response, filePath);
        System.out.println("文件 " + filePath + " 下载成功！");
    }

    public MultipartFile fileToMultipartFile(String filePath) throws IOException {
        /**
         * 注意：file名字要和参入的name一致
         */
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(),
                file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
        return multipartFile;
    }
}

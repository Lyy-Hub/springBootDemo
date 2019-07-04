package com.lyy.controller;

import com.lyy.pojo.ResponseInfo;
import com.lyy.utils.QiniuCloudUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by liyueyang on 2019/6/20.
 */
@RestController
public class QiniuyunController {

    public static byte[] imageChangeBase64(String imagePath){
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imagePath);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @RequestMapping(value="/uploadImg")
    public ResponseInfo uploadImg() {
        ResponseInfo result = new ResponseInfo();
        try {
            byte[] bytes = imageChangeBase64("G:\\ceshi.jpg");
            String imageName = UUID.randomUUID().toString();
            //使用base64方式上传到七牛云
            String url = QiniuCloudUtil.put64image(bytes, imageName);
            result.setCode(200);
            result.setInfo("文件上传成功");
            result.setInfo(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
            return result;
    }
}

package com.lyy.controller;

import com.lyy.service.api.ExcelOperationService;
import com.lyy.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * excel 导入导出
 * Created by liyueyang on 2019/6/4.
 */
@RestController
@RequestMapping("excel")
@Slf4j
public class ExcelController {
    @Autowired
    private ExcelOperationService excelOperationService;

    /**
     * 导入
     */
    @GetMapping(value = "import")
    public String importExcel(@RequestBody String fileName) {
        //fileName = "G:/test.xlsx";
        if (fileName == null && "".equals(fileName)) {
            return "文件名不能为空！";
        } else {
            if (fileName.endsWith("xls") || fileName.endsWith("xlsx")) {
                Boolean isOk = excelOperationService.importE(fileName);
                if (isOk) {
                    return "导入成功！";
                } else {
                    return "导入失败！";
                }
            }
            return "文件格式错误！";
        }
    }

    /**
     * 导出
     * @param response
     * @return
     */
    @GetMapping(value = "export")
    public String exportExcel(HttpServletResponse response) {
        String fileName = Utils.getStringDate() +  "-用户信息表.xls";
        if (fileName == null || "".equals(fileName)) {
            return "文件名不能为空！";
        } else {
            if (fileName.endsWith("xls")) {
                Boolean isOk = excelOperationService.exportE(response, fileName);
                if (isOk) {
                    return "导出成功！";
                } else {
                    return "导出失败！";
                }
            }
            return "文件格式有误！";
        }
    }

}

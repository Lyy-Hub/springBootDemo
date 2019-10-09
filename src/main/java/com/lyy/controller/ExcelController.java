package com.lyy.controller;

import com.lyy.entity.UserEntity;
import com.lyy.others.utils.ConvertDateTime;
import com.lyy.pojo.UserInfo;
import com.lyy.pojo.excelPojo.UserExcelPojo;
import com.lyy.repo.UserEntityRepo;
import com.lyy.service.api.ExcelOperationService;
import com.lyy.service.copier.UserInfoCopier;
import com.lyy.others.utils.excelUtil.easyPoi.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private UserEntityRepo userEntityRepo;
    @Autowired
    private UserInfoCopier userInfoCopier;

    /**
     * 导入
     */
    @PostMapping(value = "import")
    public String importExcel(@RequestBody String fileName) {
//        fileName = "G:/test.xlsx";
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
        String fileName = ConvertDateTime.getStringDate() +  "-用户信息表.xls";
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

    /**
     * easyPoi 导出excel
     * @param response
     */
    @GetMapping("exportUserDetail")
    public void exportUserDetail(HttpServletResponse response){
        List<UserEntity> userEntityList = userEntityRepo.findAll();
        List<UserInfo> userInfoList = new ArrayList<>();
        for(UserEntity userEntity : userEntityList){
            userInfoList.add(userInfoCopier.copy(userEntity,new UserInfo()));
        }
        exportUserDetail(userInfoList,response);
    }

    private void exportUserDetail(List<UserInfo> list, HttpServletResponse response) {
        List<UserExcelPojo> result = new ArrayList<>();
        for (UserInfo userInfo : list) {
            UserExcelPojo excel = new UserExcelPojo();
            excel.setUserName(userInfo.getUserName());
            excel.setPassword(userInfo.getPassword());
            excel.setSex(userInfo.getSex());
            excel.setAddress(userInfo.getAddress());
            excel.setStatus(userInfo.getStatus());
            excel.setCreateTime(userInfo.getCreateTime());
            result.add(excel);
        }
        FileUtil.exportExcel(result, "用户统计", "用户统计", UserExcelPojo.class, "用户统计.xls", response);
    }
}

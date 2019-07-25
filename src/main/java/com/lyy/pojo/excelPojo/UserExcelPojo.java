package com.lyy.pojo.excelPojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * Created by liyueyang on 2019/7/25.
 */
@Data
public class UserExcelPojo {

    @Excel(name = "用户名", orderNum = "0")
    private String userName;
    @Excel(name = "密码", orderNum = "2")
    private String password;
    @Excel(name = "性别",replace = {"女_0", "男_1"}, orderNum = "3")
    private String sex;
    @Excel(name = "住址", orderNum = "4")
    private String address;
    @Excel(name = "状态",replace = {"失效_0", "正常_1"},orderNum = "5")
    private String status;
    @Excel(name = "创建时间", orderNum = "6")
    private String createTime;
}

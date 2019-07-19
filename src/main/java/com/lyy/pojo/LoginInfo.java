package com.lyy.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by liyueyang on 2019/5/24.
 */
public class LoginInfo implements Serializable{
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "用户密码")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

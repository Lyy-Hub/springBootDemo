package com.lyy.others.utils.common;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by liyueyang on 2019/5/24.
 */
public class ResponseInfo {
    @ApiModelProperty(value = "状态码")
    private int code;
    @ApiModelProperty(value = "内容")
    private String info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "code=" + code +
                ", info='" + info + '\'' +
                '}';
    }
}

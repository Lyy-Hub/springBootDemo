package com.lyy.others.utils.excelUtil;

/**
 * Created by liyueyang on 2019/6/4.
 */
public class testExcelPojo {
    private String userName;
    private String address;
    private String status;
    private String createTime;
    private String other;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "testExcelPojo{" +
                "userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}

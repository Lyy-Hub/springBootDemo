package com.lyy.pojo;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by liyueyang on 2019/5/24.
 */
public class UserInfo implements Serializable{

    public interface UserInfoSimpleView{};
    public interface UserInfoDetailView extends UserInfoSimpleView{};

    private String id;
    private String userName;//用户名
    private String password;//密码 md5加密大写
    private String sex;//性别
    private String address;//住址
    private String status;//状态
    private String createTime;

    @JsonView(UserInfoSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @JsonView(UserInfoSimpleView.class)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @JsonView(UserInfoDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @JsonView(UserInfoSimpleView.class)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    @JsonView(UserInfoSimpleView.class)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @JsonView(UserInfoSimpleView.class)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @JsonView(UserInfoSimpleView.class)
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(id, userInfo.id) &&
                Objects.equals(userName, userInfo.userName) &&
                Objects.equals(password, userInfo.password) &&
                Objects.equals(sex, userInfo.sex) &&
                Objects.equals(address, userInfo.address) &&
                Objects.equals(status, userInfo.status) &&
                Objects.equals(createTime, userInfo.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, password, sex, address, status, createTime);
    }
}

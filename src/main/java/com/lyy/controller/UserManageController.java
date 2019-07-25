package com.lyy.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonView;
import com.lyy.pojo.*;
import com.lyy.service.api.UserManagementService;
import com.lyy.utils.common.PageRequest;
import com.lyy.utils.common.PageResult;
import com.lyy.utils.common.ResponseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录、增删改查操作
 * Created by liyueyang on 2019/1/18.
 */
@RestController
@RequestMapping("management")
@Api(tags = "用户管理模块")
public class UserManageController {
    @Autowired
    private UserManagementService userManagementService;

    /**
     * 用户登录
     * @param loginInfo
     * @return
     */
    @ApiOperation(value = "登录接口", notes = "用户登录")
    @ApiImplicitParam(name = "LoginInfo", value = "登录对象")
    @PostMapping(value = "login")
    public JSONObject login(@RequestBody LoginInfo loginInfo) {
        return userManagementService.login(loginInfo);
    }

    /**
     * 新增用户
     * @param userInfo
     * @return
     */
    @ApiOperation(value = "新增用户")
    @PostMapping(value = "addUser")
    public ResponseInfo addUser(@ApiParam(value = "用户实体") @RequestBody UserInfo userInfo){
        return userManagementService.addUser(userInfo);
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @ApiOperation(value = "删除用户")
    @GetMapping(value="deleteUser")
    public ResponseInfo deleteUser(@ApiParam(value = "用户ID数组") @RequestBody String[] ids){
        return userManagementService.deleteUser(ids);
    }

    /**
     * 编辑用户
     * @param userInfo
     * @return
     */
    @ApiOperation(value = "编辑用户")
    @PostMapping(value = "updateUser")
    public ResponseInfo updateUser(@ApiParam(value = "用户实体") @RequestBody UserInfo userInfo){
        return userManagementService.updateUser(userInfo);
    }

    /**
     * 查询用户
     * @param param
     * @return
     */
    @ApiOperation(value = "查询用户")
    @GetMapping(value="findUser")
    //@UserLoginToken
    public PageResult<UserInfo> findUser(@ApiParam(value = "分页参数与查询条件") @RequestBody PageRequest<UserParam> param){
        return userManagementService.findUser(param);
    }

    /**
     * 查询一个用户
     */
    @ApiOperation(value = "查询单个用户",notes = "仅测试，无具体业务意义")
    @GetMapping(value="findOneUser")
    @JsonView(UserInfo.UserInfoSimpleView.class)
    public UserInfo findOneUser(){
        return userManagementService.findOneUser();
    }
}

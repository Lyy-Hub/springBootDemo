package com.lyy.controller;

import com.lyy.pojo.*;
import com.lyy.service.UserManagementService;
import org.coodex.concrete.api.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liyueyang on 2019/1/18.
 */
@RestController
@RequestMapping("management")
@Description(name = "用户登录、增删改查操作")
public class UserManagementController {
    @Autowired
    private UserManagementService applicationService;

    @Description(name = "用户登录")
    @PostMapping(value = "login")
    public ResponseInfo login(@RequestBody LoginInfo loginInfo) {
        return applicationService.login(loginInfo);
    }

    @Description(name = "新增用户")
    @PostMapping(value = "addUser")
    public ResponseInfo addUser(@RequestBody UserInfo userInfo){
        return applicationService.addUser(userInfo);
    }

    @Description(name = "删除用户")
    @GetMapping(value="deleteUser")
    public ResponseInfo deleteUser(@RequestBody String[] ids){
        return applicationService.deleteUser(ids);
    }

    @Description(name = "编辑用户")
    @PostMapping(value = "updateUser")
    public ResponseInfo updateUser(@RequestBody UserInfo userInfo){
        return applicationService.updateUser(userInfo);
    }

    @Description(name = "查询用户")
    @GetMapping(value="findUser")
    public PageResult<UserInfo> findUser(@RequestBody PageRequest<UserParam> param){
        return applicationService.findUser(param);
    }
}

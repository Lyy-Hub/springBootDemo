package com.lyy.controller;

import com.alibaba.fastjson.JSONObject;
import com.lyy.Interface.Signature;
import com.lyy.Interface.UserLoginToken;
import com.lyy.pojo.*;
import com.lyy.service.api.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录、增删改查操作
 * Created by liyueyang on 2019/1/18.
 */
@RestController
@RequestMapping("management")
public class UserManagementController {
    @Autowired
    private UserManagementService userManagementService;

    /**
     * 用户登录
     * @param loginInfo
     * @return
     */
    @PostMapping(value = "login")
    public JSONObject login(@RequestBody LoginInfo loginInfo) {
        return userManagementService.login(loginInfo);
    }

    /**
     * 新增用户
     * @param userInfo
     * @return
     */
    @PostMapping(value = "addUser")
    public ResponseInfo addUser(@RequestBody UserInfo userInfo){
        return userManagementService.addUser(userInfo);
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @GetMapping(value="deleteUser")
    public ResponseInfo deleteUser(@RequestBody String[] ids){
        return userManagementService.deleteUser(ids);
    }

    /**
     * 编辑用户
     * @param userInfo
     * @return
     */
    @PostMapping(value = "updateUser")
    public ResponseInfo updateUser(@RequestBody UserInfo userInfo){
        return userManagementService.updateUser(userInfo);
    }

    /**
     * 查询用户
     * @param param
     * @return
     */
    @GetMapping(value="findUser")
    //@UserLoginToken
    public PageResult<UserInfo> findUser(@RequestBody PageRequest<UserParam> param){
        return userManagementService.findUser(param);
    }
}

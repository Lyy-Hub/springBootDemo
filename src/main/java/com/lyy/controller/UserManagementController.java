package com.lyy.controller;

import com.lyy.pojo.*;
import com.lyy.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liyueyang on 2019/1/18.
 */
@RestController
@RequestMapping("management")
public class UserManagementController {
    @Autowired
    private UserManagementService applicationService;

    @PostMapping(value = "login")
    public ResponseInfo login(@RequestBody LoginInfo loginInfo) {
        return applicationService.login(loginInfo);
    }

    @PostMapping(value = "updateUser")
    public ResponseInfo updateUser(@RequestBody UserInfo userInfo){
        return applicationService.updateUser(userInfo);
    }

    @PostMapping(value = "addUser")
    public ResponseInfo addUser(@RequestBody UserInfo userInfo){
        return applicationService.addUser(userInfo);
    }

    @GetMapping(value="findUser")
    public PageResult<UserInfo> findUser(@RequestBody PageRequest<UserParam> param){
        return applicationService.findUser(param);
    }

    @GetMapping(value="deleteUser")
    public ResponseInfo deleteUser(@RequestBody String[] ids){
        return applicationService.deleteUser(ids);
    }
}

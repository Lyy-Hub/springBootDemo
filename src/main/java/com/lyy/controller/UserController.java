package com.lyy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyueyang on 2019/7/19.
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户控制器")
public class UserController {
    @ApiOperation(value = "打招呼", notes = "测试方法")
    @ApiImplicitParam(name = "name", value = "姓名")
    @RequestMapping(value = "/sayhi", method = RequestMethod.POST)
    public String sayHi(String name) {
        return "Hello," + name;
    }

    @ApiOperation(value = "获取所有用户", notes = "查询分页数据")
    @RequestMapping(value = "/getall", method = RequestMethod.POST)
    public String getAll() {
        return "所有用户";
    }
}

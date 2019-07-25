package com.lyy.service.api;

import com.alibaba.fastjson.JSONObject;
import com.lyy.pojo.*;
import com.lyy.utils.common.PageRequest;
import com.lyy.utils.common.PageResult;
import com.lyy.utils.common.ResponseInfo;

import java.util.List;

/**
 * Created by liyueyang on 2019/6/12.
 */
public interface UserManagementService {

    JSONObject login(LoginInfo loginInfo);

    ResponseInfo addUser(UserInfo userInfo);

    ResponseInfo deleteUser(String[] ids);

    ResponseInfo updateUser(final UserInfo userInfo);

    PageResult<UserInfo> findUser(PageRequest<UserParam> param);

    List<UserInfo> findAll();

    UserInfo findOneUser();
}

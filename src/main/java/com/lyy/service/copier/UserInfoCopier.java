package com.lyy.service.copier;

import com.lyy.entity.UserEntity;
import com.lyy.others.utils.ConvertDateTime;
import com.lyy.pojo.UserInfo;
import org.springframework.stereotype.Component;

/**
 * Created by liyueyang on 2019/6/3.
 */
@Component
public class UserInfoCopier extends AbstractCopier<UserEntity, UserInfo> {
    @Override
    public UserInfo copy(UserEntity userEntity, UserInfo userInfo) {
        userInfo.setId(userEntity.getId());
        userInfo.setUserName(userEntity.getUserName());
        userInfo.setPassword(userEntity.getPassword());
        userInfo.setSex(userEntity.getSex());
        userInfo.setStatus(userEntity.getStatus());
        userInfo.setAddress(userEntity.getAddress());
        userInfo.setCreateTime(ConvertDateTime.calender2Str(userEntity.getCreateTime()));
        return userInfo;
    }
}

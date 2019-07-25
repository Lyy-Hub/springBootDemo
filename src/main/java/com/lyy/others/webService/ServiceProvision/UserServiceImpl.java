package com.lyy.others.webService.ServiceProvision;

import com.lyy.entity.UserEntity;
import com.lyy.pojo.UserInfo;
import com.lyy.repo.UserEntityRepo;
import com.lyy.service.copier.UserInfoCopier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * Created by liyueyang on 2019/6/14.
 */
@WebService(serviceName="UserService",//对外发布的服务名
                  targetNamespace="http://ServiceProvision.webService.lyy.com",//指定你想要的名称空间，通常使用使用包名反转
                    endpointInterface= "com.lyy.others.webService.ServiceProvision.UserService")//服务接口全路径, 指定做SEI（Service EndPoint Interface）服务端点接口
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserEntityRepo userEntityRepo;
    @Autowired
    private UserInfoCopier userInfoCopier;

    @Override
    public UserInfo getUserById(String id) {
        UserEntity userEntity = userEntityRepo.findOne(id);
        UserInfo userInfo = userInfoCopier.copy(userEntity, new UserInfo());
        return userInfo;
    }
}

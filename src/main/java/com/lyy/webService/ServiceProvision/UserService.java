package com.lyy.webService.ServiceProvision;

import com.lyy.pojo.UserInfo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Created by liyueyang on 2019/6/14.
 */
@WebService(targetNamespace="http://ServiceProvision.webService.lyy.com")
public interface UserService {

    @WebMethod
    public UserInfo getUserById(@WebParam(name = "id") String id);
}

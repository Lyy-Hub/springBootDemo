package com.lyy.others.webService.ServiceInvocation.byWsdl;

import com.lyy.others.webService.ServiceInvocation.byWsdl.com.example.demo.request.ArrayOfString;
import com.lyy.others.webService.ServiceInvocation.byWsdl.com.example.demo.request.IpAddressSearchWebServiceSoap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyueyang on 2019/6/14.
 */
@RestController
@RequestMapping("/soap")
public class DemoApplication {

    @Autowired
    private IpAddressSearchWebServiceSoap soap;

    @RequestMapping("/{ip}")
    public ArrayOfString searchIp(@PathVariable("ip") String ip) {
        ArrayOfString response = soap.getCountryCityByIp(ip);
        return response;
    }
}


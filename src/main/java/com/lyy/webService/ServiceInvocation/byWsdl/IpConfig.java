package com.lyy.webService.ServiceInvocation.byWsdl;

import com.lyy.webService.ServiceInvocation.byWsdl.com.example.demo.request.IpAddressSearchWebService;
import com.lyy.webService.ServiceInvocation.byWsdl.com.example.demo.request.IpAddressSearchWebServiceSoap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liyueyang on 2019/6/14.
 */
@Configuration
public class IpConfig {

    @Bean
    public IpAddressSearchWebServiceSoap webService(){
        return new IpAddressSearchWebService().getIpAddressSearchWebServiceSoap();
    }
}

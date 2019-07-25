package com.lyy.others.webService.ServiceInvocation.byHttpUrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 远程服务调用
 * Created by liyueyang on 2019/6/14.
 */
@RestController
public class HelloClientController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 远程服务调用
     * get（getForObject、getForEntity）
     * post（postForLocation、postForObject）
     * put（put）
     * delete（delete）
     * @return 接口返回的信息
     */
    @RequestMapping(value = "/getMessage")
    public String getMessage() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8088/haohao", String.class);
        String msg = responseEntity.getBody();
        return msg;
    }
}

package com.lyy.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by liyueyang on 2019/5/27.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    private JwtFilter jwtFilter;
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        //  开发阶段先注释掉，方便调试
        interceptorRegistry.addInterceptor(jwtFilter)
                .addPathPatterns("/**");
    }

}

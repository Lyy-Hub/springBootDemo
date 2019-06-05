package com.lyy.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by liyueyang on 2019/5/27.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

    @Autowired
    private JwtFilter jwtFilter;
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(jwtFilter)
                .addPathPatterns("/**").excludePathPatterns("/rabbitMq/send");
    }

}

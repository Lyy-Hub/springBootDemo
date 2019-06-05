package com.lyy;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by liyueyang on 2019/1/18.
 */
@SpringBootApplication
@ImportResource(locations = "spring.xml")
@EnableRabbit
public class Start extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Start.class);
    }


    public static void main(String[] args){
        SpringApplication.run(Start.class);
    }

}

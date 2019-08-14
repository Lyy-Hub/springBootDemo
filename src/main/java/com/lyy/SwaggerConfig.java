package com.lyy;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.google.common.base.Predicates;

/**
 * Created by liyueyang on 2019/7/19.
 */
@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger.enable:true}")
public class SwaggerConfig {
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot APIs",
                "Spring Boot + Swagger2",
                "1.0.0",
                null,
                "LYY-DEMO",
                "作者：LYY",
                "https://www.baidu.com/");

        com.google.common.base.Predicate<RequestHandler> selector1 = RequestHandlerSelectors.basePackage("com.lyy.controller");
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(Predicates.or(selector1))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo)
                .useDefaultResponseMessages(false);
        return docket;
    }
}

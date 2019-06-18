package com.lyy.Interface;

import java.lang.annotation.*;

/**
 * 跳过验证登录token
 * Created by liyueyang on 2019/6/18.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PassToken {
    boolean required() default true;
}

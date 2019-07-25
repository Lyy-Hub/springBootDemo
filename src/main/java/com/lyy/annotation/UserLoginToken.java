package com.lyy.annotation;

import java.lang.annotation.*;

/**
 * 需要验证登录token
 * Created by liyueyang on 2019/6/18.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserLoginToken {
    boolean required() default true;
}

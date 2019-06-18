package com.lyy.Interface;

import java.lang.annotation.*;

/**
 * 拦截请求地址，记录日志
 * Created by liyueyang on 2019/6/18.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Signature {
    String value() default "";
}

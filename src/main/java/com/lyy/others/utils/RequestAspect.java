package com.lyy.others.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Created by liyueyang on 2019/6/18.
 */
@Aspect
@Component
@Slf4j
public class RequestAspect {

    /**
     * 表示在执行被@MonitorRequest注解修饰的方法之前 会执行doBefore()方法
     *
     * @param joinPoint 连接点，就是被拦截点
     */
    /*@Before(value = "@annotation(com.lyy.Interface.Signature)")
    public void doBefore(JoinPoint joinPoint) {
        //获取到请求的属性
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取到请求对象
        HttpServletRequest request = attributes.getRequest();
        //URL：根据请求对象拿到访问的地址
        log.info("url=" + request.getRequestURL());
        //获取请求的方法，是Get还是Post请求
        log.info("method=" + request.getMethod());
        //ip：获取到访问
        log.info("ip=" + request.getRemoteAddr());
        //获取被拦截的类名和方法名
        log.info("class=" + joinPoint.getSignature().getDeclaringTypeName() +
                "and method name=" + joinPoint.getSignature().getName());
        //参数
        log.info("参数=" + joinPoint.getArgs().toString());
    }*/

    @Pointcut(value = "@annotation(com.lyy.others.annotation.Signature)")
    public void annotationPointCut() {
    }

    @Around("annotationPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        System.out.println("方法名：" + methodName);
        if(!validate()){
            return "没有权限";
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            return null;
        }
    }

    private boolean validate(){
        // TODO 实现自己的鉴权功能
        return true;
    }
}

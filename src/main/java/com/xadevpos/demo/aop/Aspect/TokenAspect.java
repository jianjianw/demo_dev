package com.xadevpos.demo.aop.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
//@Aspect
//@Component
//@Order(1)
public class TokenAspect {

    @Pointcut("execution(* com.xadevpos.demo.controller.*.*(..))")
    public void token() {
    }

    @Around("token()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("bef");
        Object result = joinPoint.proceed();
        System.out.println("after");
        return result;
    }




}

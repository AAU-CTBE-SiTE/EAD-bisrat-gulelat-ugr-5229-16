package com.craft.lab6.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.craft.lab6.service.*.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("BEFORE: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.craft.lab6.service.*.*(..))")
    public void afterMethod(JoinPoint joinPoint) {
        System.out.println("AFTER: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(
            pointcut = "execution(* com.craft.lab6.service.*.*(..))",
            returning = "result"
    )
    public void afterReturning(Object result) {
        System.out.println("SUCCESS: " + result);
    }

    @AfterThrowing(
            pointcut = "execution(* com.craft.lab6.service.*.*(..))",
            throwing = "exception"
    )
    public void afterThrowing(Exception exception) {
        System.out.println("ERROR: " + exception.getMessage());
    }

    @Around("execution(* com.craft.lab6.service.*.*(..))")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("AROUND: Starting method");

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println("AROUND: Execution time = " + (end - start) + " ms");

        return result;
    }
}
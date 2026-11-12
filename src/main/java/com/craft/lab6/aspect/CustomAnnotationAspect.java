
package com.craft.lab6.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class CustomAnnotationAspect {

    @Around("@annotation(com.craft.lab6.aspect.TrackExecutionTime)")
    public Object trackExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println("CUSTOM ANNOTATION: Starting "
                + joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println("CUSTOM ANNOTATION: Execution time = "
                + (end - start) + " ms");

        return result;
    }
}

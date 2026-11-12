
package com.craft.lab6.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class LoggingAspect {

    @Pointcut("execution(* com.craft.lab6.service.*.*(..))")
    public void studentServiceMethods() {
    }

    @Pointcut("within(com.craft.lab6.service..*)")
    public void withinServicePackage() {
    }

    @Pointcut("args(String)")
    public void methodsWithStringArgument() {
    }

    @Pointcut("@annotation(com.craft.lab6.aspect.TrackExecutionTime)")
    public void methodsWithTrackAnnotation() {
    }

    @Before("studentServiceMethods()")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("BEFORE: " + joinPoint.getSignature().getName());
    }

    // Access method arguments
    @Before("studentServiceMethods()")
    public void logArguments(JoinPoint joinPoint) {
        System.out.println("METHOD ARGUMENTS: "
                + java.util.Arrays.toString(joinPoint.getArgs()));
    }

    @After("studentServiceMethods()")
    public void afterMethod(JoinPoint joinPoint) {
        System.out.println("AFTER: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(
            pointcut = "studentServiceMethods()",
            returning = "result"
    )
    public void afterReturning(Object result) {
        System.out.println("SUCCESS: " + result);
    }

    @AfterThrowing(
            pointcut = "studentServiceMethods()",
            throwing = "exception"
    )
    public void afterThrowing(Exception exception) {
        System.out.println("ERROR: " + exception.getMessage());
    }

    @Around("studentServiceMethods()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("AROUND: Starting method");

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println("AROUND: Execution time = "
                + (end - start) + " ms");

        return result;
    }
}

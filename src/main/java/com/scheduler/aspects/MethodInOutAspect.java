package com.scheduler.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


@Slf4j
@Aspect
public class MethodInOutAspect {
    @Before("execution(* *(..)) && @annotation(com.scheduler.aspects.LogInOut)")
    public void before(JoinPoint joinPoint){
        log.info("method {} called with args ", joinPoint.getSignature().getName());
        Object[] signatureArgs = joinPoint.getArgs();
        for (Object signatureArg: signatureArgs) {
            log.info("Arg: " + signatureArg);
        }
    }

    @AfterReturning(value = "execution(* *(..)) && @annotation(com.scheduler.aspects.LogInOut)", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        log.info("method {} returned {}", joinPoint.getSignature().getName(),result);
    }
}

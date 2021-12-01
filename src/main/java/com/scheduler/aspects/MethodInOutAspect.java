package com.scheduler.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Aspect
@Configuration
public class MethodInOutAspect {
    @Before("execution(* *(..)) && @annotation(com.scheduler.aspects.LogInOut)")
    public void before(JoinPoint joinPoint){
        String str = "method {} called";
        Object[] signatureArgs = joinPoint.getArgs();
        String[] splittedSignature = joinPoint.getSignature().toString().split("[\\(\\)]");
        if (splittedSignature.length > 1) {
            str += " with args (";
            String[] argsTypes = splittedSignature[1].split(",");
            str += argsTypes[0] + ' ' + signatureArgs[0];
            for (int i = 1; i < signatureArgs.length; i++) {
                str += ", " + argsTypes[i] + ' ' + signatureArgs[i];
            }
            str += ");";
        }
        log.info(str, joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* *(..)) && @annotation(com.scheduler.aspects.LogInOut)", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        log.info("method {} returned {}", joinPoint.getSignature().getName(),result);
    }
}

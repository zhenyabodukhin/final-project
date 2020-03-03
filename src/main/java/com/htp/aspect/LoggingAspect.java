package com.htp.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private MethodCallCounterImpl methodCallCounter;


    @Around("execution(* com.htp.dao.impl.UserRepositoryImpl.*(..))")
    public Object logBefore(ProceedingJoinPoint pjp) throws Throwable {
        String method = pjp.getSignature().getName();
        methodCallCounter.count(method);
        return pjp.proceed();
    }

}

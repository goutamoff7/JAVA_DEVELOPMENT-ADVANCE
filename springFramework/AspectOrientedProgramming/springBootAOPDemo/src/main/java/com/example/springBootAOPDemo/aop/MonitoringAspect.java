package com.example.springBootAOPDemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MonitoringAspect {

    //using @Slf4j annotation of lombok
//    public static final Logger LOGGER = LoggerFactory.getLogger(MonitoringAspect.class);

    @Around("execution(* com.example.springBootAOPDemo.service.JobService.*(..))")
    public Object calculateExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("Time taken by : " + joinPoint.getSignature().getName() + "=" + (end-start) + " ms");
        return result;
    }
}

package com.example.springBootAOPDemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    // return-type class-name.method-name(args)
//    All methods of JobService
    @Before("execution(* com.example.springBootAOPDemo.service.JobService.*(..))")
    public void logMethodCall(){
        LOGGER.info("Method Called");
    }

//    getJob and getAllJob method of JobService - Logs irrespective of exception arise or not (like finally)
    @After("execution(* com.example.springBootAOPDemo.service.JobService.getJob(..)) || " +
            "execution(* com.example.springBootAOPDemo.service.JobService.getAllJobs(..))")
    public void logMethodExecuted(JoinPoint joinPoint){
        LOGGER.info("Method Executed " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.example.springBootAOPDemo.service.JobService.getJob(..)) || " +
            "execution(* com.example.springBootAOPDemo.service.JobService.getAllJobs(..))")
    public void logMethodSuccess(JoinPoint joinPoint){
        LOGGER.info("Method Executed Successfully " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(
            pointcut="execution(* com.example.springBootAOPDemo.service.JobService.searchByKeyword(..))",
            throwing="e")
    public void logMethodError(JoinPoint joinPoint ,Exception e){
        LOGGER.error("Method has some issue " + joinPoint.getSignature().getName() + " " + e.getMessage());
    }

}

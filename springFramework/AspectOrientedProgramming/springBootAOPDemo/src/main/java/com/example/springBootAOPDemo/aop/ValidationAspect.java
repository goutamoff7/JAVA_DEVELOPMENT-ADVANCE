package com.example.springBootAOPDemo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.example.springBootAOPDemo.service.JobService.getJob(..)) && args(postId)")
    public Object validateField(ProceedingJoinPoint proceedingJoinPoint, int postId) throws Throwable {
        if(postId<0) {
            LOGGER.info("postId is negetive, Updating it");
            postId = -postId;
            LOGGER.info("new value : "+postId);
        }
        Object[] variables = {postId};
        Object result = proceedingJoinPoint.proceed(variables);
//       Object result =  proceedingJoinPoint.proceed(new Object[]{postId});
        return result;
    }

}

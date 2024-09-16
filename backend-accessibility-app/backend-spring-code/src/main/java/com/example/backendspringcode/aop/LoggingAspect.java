package com.example.backendspringcode.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j// simple logging facade for Java
@Aspect // is ready to spy on other classes   🧐
@Component // tells Spring to create bean of that class
/* Upon application launch, Spring scans for classes that are candidates for becoming a bean by checking for annotations like @Component
* Spring creates instances of those classes aka beans  */
public class LoggingAspect {

    // Pointcut tels it where to spy; join point= method executions being intercepted
    @Pointcut("execution(* com.example.backendspringcode.controller.*.*(..)) || execution(* com.example.backendspringcode.service.*.*(..))")
    public void controllerAndServiceMethods() {}

    // method will be invoked "around" (before and after the join points)
    @Around("controllerAndServiceMethods()")
    public Object logMethodDetails(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Before method: " + proceedingJoinPoint.getSignature().toShortString() + "; Trigger time: " + System.currentTimeMillis()); // before invoking intercepted method, it logs a message that method is about to be executed
        try {
            Object result = proceedingJoinPoint.proceed(); // then proceed with execution of intercepted method
            log.info("Method executed successfully" + proceedingJoinPoint.getSignature().toShortString() + "; Trigger time: " + System.currentTimeMillis()); // after execution logs a message to say it was successful
            return result;
        } catch (Exception ex) { //else it throws at
            log.error("Method threw an exception: " + ex.getMessage()); //
            throw ex;
        }
    }
}


package com.example.springbootboostrap.aspect;

import com.example.springbootboostrap.dto.request.BaseRequest;
import com.example.springbootboostrap.dto.response.BaseResponse;
import com.example.springbootboostrap.util.AppUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Aspect
@Component
public class RequestResponseAspect {

    private static final ThreadLocal<Date> requestTime = new ThreadLocal<>();
    private static final ThreadLocal<String> requestId = new ThreadLocal<>();

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    private void controller() {
    }

    @Pointcut("execution(* *(..))")
    private void allMethod() {
    }

    @Before("controller() && allMethod()")
    public void processRequest(JoinPoint joinPoint) {
        requestTime.set(new Date());
        requestId.set(UUID.randomUUID().toString());
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BaseRequest) {
                BaseRequest request = (BaseRequest) arg;
                request.setRequestId(requestId.get());
                request.setRequestTime(requestTime.get());
            }
        }
    }

    @AfterReturning(pointcut = "controller() && allMethod()", returning = "result")
    public void processResponse(JoinPoint joinPoint, Object result) {
        if(((ResponseEntity) result).getBody() instanceof BaseResponse) {
            BaseResponse response = (BaseResponse) ((ResponseEntity) result).getBody();
            response.setRequestId(requestId.get());
            response.setRequestTime(requestTime.get());
            response.setResponseTime(new Date());
            response.setProcessingTime(AppUtil.getTimeDifferenceInSeconds(response.getRequestTime(), response.getResponseTime()));
        }
    }
}


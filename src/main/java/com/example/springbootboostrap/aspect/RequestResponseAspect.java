package com.example.springbootboostrap.aspect;

import com.example.springbootboostrap.dto.request.BaseRequest;
import com.example.springbootboostrap.dto.response.BaseResponse;
import com.example.springbootboostrap.util.AppUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Aspect
@Component
@Slf4j
public class RequestResponseAspect {

    private static final ThreadLocal<Date> requestTime = new ThreadLocal<>();
    private static final ThreadLocal<String> requestId = new ThreadLocal<>();

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    private void controller() {
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestControllerAdvice *)")
    private void controllerAdvice() {
    }

    @Pointcut("execution(* *(..))")
    private void allMethod() {
    }

    @Before("controller() && allMethod()")
    public void processRequest(JoinPoint joinPoint) {
        requestTime.set(new Date());
        requestId.set(UUID.randomUUID().toString());
        final Object[] args = joinPoint.getArgs();
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
            final BaseResponse response = (BaseResponse) ((ResponseEntity) result).getBody();
            response.setRequestId(requestId.get());
            response.setRequestTime(requestTime.get());
            response.setResponseTime(new Date());
            response.setProcessingTime(AppUtil.getTimeDifferenceInSeconds(response.getRequestTime(), response.getResponseTime()));
        }
    }

    @AfterReturning(pointcut = "controllerAdvice() && allMethod()", returning = "result")
    public void processErrorResponse(JoinPoint joinPoint, Object result) {
        if(((ResponseEntity) result).getBody() instanceof BaseResponse) {
            final BaseResponse response = (BaseResponse) ((ResponseEntity) result).getBody();
            response.setHttpStatus(((ResponseEntity<?>) result).getStatusCode());
            response.setRequestId(requestId.get());
            response.setRequestTime(requestTime.get());
            response.setResponseTime(new Date());
            if (Objects.nonNull(response.getRequestTime()) && Objects.nonNull(response.getResponseTime())) {
                response.setProcessingTime(AppUtil.getTimeDifferenceInSeconds(response.getRequestTime(), response.getResponseTime()));
            }
        }
    }
}


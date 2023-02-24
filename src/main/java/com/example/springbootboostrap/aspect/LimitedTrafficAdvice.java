package com.example.springbootboostrap.aspect;


import com.example.springbootboostrap.annotation.LimitedTraffic;
import com.example.springbootboostrap.annotationprocessor.LimitedTrafficAnnotationProcessor;
import com.example.springbootboostrap.exception.RequestNotPermittedException;
import com.example.springbootboostrap.util.LogUtil;
import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class LimitedTrafficAdvice {

    private final LimitedTrafficAnnotationProcessor limitedTrafficAnnotationProcessor;

    @Pointcut("@annotation(com.robi.da.validator.LimitedTraffic)")
    private void limitTraffic() {
    }

    @Before("limitTraffic() && @annotation(limitedTraffic)")
    public void validateRequest(JoinPoint joinPoint, LimitedTraffic limitedTraffic) {
        final Bucket bucket = limitedTrafficAnnotationProcessor.getBucket(limitedTraffic.endpointName());
        if (bucket.tryConsume(1)) {
            LogUtil.logInfo(log, "Consumed token || Remaining tokens : " + bucket.getAvailableTokens());
        } else {
            LogUtil.logInfo(log, "No available tokens");
            throw new RequestNotPermittedException("Too many requests");
        }
    }
}


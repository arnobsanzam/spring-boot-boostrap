package com.example.springbootboostrap.annotationprocessor;


import com.example.springbootboostrap.annotation.LimitedTraffic;
import com.example.springbootboostrap.enums.BandwidthType;
import com.example.springbootboostrap.exception.BucketRetrievalFailedException;
import com.example.springbootboostrap.util.LogUtil;
import io.github.bucket4j.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class LimitedTrafficAnnotationProcessor {

    private final Map<String, Bucket> bucketMap = new HashMap<>();

    @PostConstruct
    void startProcessing() {
        try {
            LogUtil.logInfo(log, "Starting Bucket initialization");
            final Reflections reflections = new Reflections(new MethodAnnotationsScanner());
            final Set<Method> annotatedMethods = reflections.getMethodsAnnotatedWith(LimitedTraffic.class);
            for (Method method : annotatedMethods) {
                final LimitedTraffic limitedTraffic = method.getAnnotation(LimitedTraffic.class);
                final String endpoint = limitedTraffic.endpointName();
                final BandwidthType bandwidthType = limitedTraffic.bandwidthType();
                final Bucket bucket = Bucket.builder().addLimit(bandwidthType.getLimit()).build();
                bucketMap.put(endpoint, bucket);
            }
            LogUtil.logInfo(log, "Finished Bucket initialization");
        } catch (Exception ex) {
            LogUtil.logError(log, "Failed to process LimitedTraffic annotation during start-up " + ex.getMessage());
        } finally {
            LogUtil.logInfo(log, "Bucket Map : " + bucketMap);
        }
    }

    public Bucket getBucket(String endpoint) {
        if (bucketMap.containsKey(endpoint)) {
            return bucketMap.get(endpoint);
        }
        LogUtil.logInfo(log, "Bucket not found with endpoint : " + endpoint);
        throw new BucketRetrievalFailedException("Bucket not found with specified configuration");
    }
}

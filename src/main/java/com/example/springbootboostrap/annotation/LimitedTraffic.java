package com.example.springbootboostrap.annotation;

import com.example.springbootboostrap.enums.BandwidthType;

import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitedTraffic {

    String endpointName() default "/";

    BandwidthType bandwidthType();

    String message() default "Too many requests";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

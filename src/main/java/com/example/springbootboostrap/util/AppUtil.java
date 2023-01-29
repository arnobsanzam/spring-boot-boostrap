package com.example.springbootboostrap.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class AppUtil {
    public static long getTimeDifferenceInSecond(Date date1, Date date2) {
        Instant instant1 = date1.toInstant();
        Instant instant2 = date2.toInstant();
        Duration duration = Duration.between(instant1, instant2);
        return duration.getSeconds();
    }
}

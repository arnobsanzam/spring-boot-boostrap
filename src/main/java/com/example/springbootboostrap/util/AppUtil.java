package com.example.springbootboostrap.util;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class AppUtil {

    public static long getTimeDifferenceInSeconds(Date date1, Date date2) {
        Instant instant1 = date1.toInstant();
        Instant instant2 = date2.toInstant();
        Duration duration = Duration.between(instant1, instant2);
        return duration.getSeconds();
    }

    public static long getTimeDifferenceInMinutes(Date date1, Date date2) {
        return Duration.between(date1.toInstant(), date2.toInstant()).toMinutes();
    }
}

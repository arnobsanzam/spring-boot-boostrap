package com.example.springbootboostrap.enums;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;

import java.time.Duration;

public enum BandwidthType {
    FIVE_REQUESTS_PER_SECOND {
        @Override
        public Bandwidth getLimit() {
            return Bandwidth.classic(5, Refill.intervally(5, Duration.ofSeconds(1)));
        }
    },

    FIVE_REQUESTS_PER_MINUTE {
        @Override
        public Bandwidth getLimit() {
            return Bandwidth.classic(5, Refill.intervally(5, Duration.ofMinutes(1)));
        }
    };

    public abstract Bandwidth getLimit();
}

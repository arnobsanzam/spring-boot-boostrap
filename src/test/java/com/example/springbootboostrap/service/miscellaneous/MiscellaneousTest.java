package com.example.springbootboostrap.service.miscellaneous;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class MiscellaneousTest {

    @Test
    public void testStringImmutability() {
        String s1 = "hello";
        String s2 = s1;
        System.out.printf(" " + s1.hashCode());
        System.out.printf(" " + s2.hashCode());
    }
}


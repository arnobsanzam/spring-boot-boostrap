package com.example.springbootboostrap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@ExtendWith(MockitoExtension.class)
class StringTest {

    @Test
    public void startTesting() {
       String s1 = "abcdef";
       final String s2 = "abc";
       final String s3 = "def";
       final String s4 = "123";

       System.out.println(s1 == s2 + s3);

        assertNotNull("");
        assertEquals("", "");
    }
}

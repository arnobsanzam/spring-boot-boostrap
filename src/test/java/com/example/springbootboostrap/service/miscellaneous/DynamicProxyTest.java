package com.example.springbootboostrap.service.miscellaneous;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@Slf4j
class DynamicProxyTest {

    @Test
    public void demo() {
        assertNotNull("");
        assertEquals("", "");
    }

    interface Calculator {
        int sum(int a, int b);
    }

    class LoggingHandler implements InvocationHandler {
        Object target;

        public LoggingHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            log.info("Method name : " + method.getName());
            log.info("Arguments: ");
            for (Object arg : args) {
                log.info(arg.toString());
            }
            final Object result = method.invoke(target, args);

            return result;
        }
    }

    class CalculatorImpl implements Calculator {
        @Override
        public int sum(int a, int b) {
            return a + b;
        }
    }

    @Test
    public void testEverything() {
        CalculatorImpl calculator = new CalculatorImpl();
        LoggingHandler loggingHandler = new LoggingHandler(calculator);

        Calculator proxy = (Calculator) Proxy.newProxyInstance(Calculator.class.getClassLoader(), new Class[]{Calculator.class}, loggingHandler);
        int result = proxy.sum(2, 3);
    }
}
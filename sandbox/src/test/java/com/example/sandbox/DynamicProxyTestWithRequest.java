package com.example.sandbox;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@Slf4j
class DynamicProxyTestWithRequest {

    @Test
    public void startTesting() {
        SampleRequest request = new SampleRequest();
        request.setContent("Lorem ipsum");
        controller(request);
    }

    @Data
    class Request {
        private String requestId;
        private Date requestTime;
    }

    @Data
    class SampleRequest extends Request {
        private String content;
    }

    void controller(Request request) {
        Service service = new ServiceImpl();
        RequestHandler requestHandler = new RequestHandler(service);
        Service serviceProxy = (Service) Proxy.newProxyInstance(Service.class.getClassLoader(), new Class[] {Service.class}, requestHandler);
        serviceProxy.service(request);
    }

    interface Service {
        void service(Request request);
    }
    class ServiceImpl implements Service {
        @Override
        public void service(Request request) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                log.info("Request object : {} ", objectMapper.writeValueAsString(request));
            } catch (Exception ex) {
                log.error("Json parsing error");
            }
        }

        void welcome() {
            System.out.println("Hi!");
        }
    }


    class RequestHandler implements InvocationHandler {

        Object target;
        RequestHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Request request = null;
            for (Object arg : args) {
                if (arg instanceof Request) {
                    request = (Request) arg;
                    request.setRequestId(UUID.randomUUID().toString());
                    request.setRequestTime(new Date());
                }
            }
            return method.invoke(target, request);
        }
    }
}
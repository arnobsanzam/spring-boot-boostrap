package com.example.springbootboostrap.util;


import org.slf4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class LogUtil {

    public static void logInfo(Logger logger, String message) {
        logger.info(message);
    }

    public static void logRequestDetails(Logger logger, HttpRequest request, byte[] body) {
        logger.info("Request URI: {}", request.getURI());
        logger.info("Request Method: {}", request.getMethod());
        logger.info("Request Headers: {}", request.getHeaders());
        logger.info("Request Body: {}", new String(body));
    }

    private static void logResponseDetails(Logger logger, ClientHttpResponse response, long executionTime) throws IOException, IOException {
        logger.info("Response Status Code: {}", response.getStatusCode());
        logger.info("Response Status Text: {}", response.getStatusText());
        logger.info("Response Headers: {}", response.getHeaders());
        logger.info("Response Body: {}", response.getBody());
        logger.info("Execution Time: {} ms", executionTime);
    }
}

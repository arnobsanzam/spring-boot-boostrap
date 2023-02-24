package com.example.springbootboostrap.exception.exceptionhandler;

import com.example.springbootboostrap.constant.AppConstant;
import com.example.springbootboostrap.dto.response.error.ErrorResponse;
import com.example.springbootboostrap.exception.BucketRetrievalFailedException;
import com.example.springbootboostrap.exception.EntityNotFoundException;
import com.example.springbootboostrap.exception.RequestNotPermittedException;
import com.example.springbootboostrap.util.LogUtil;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice(basePackages = {"com.example.springbootboostrap"})
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse
                        .builder()
                        .error(AppConstant.Exception.ENTITY_NOT_FOUND)
                        .debugMessage(ex.getLocalizedMessage()).build());
    }

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<ErrorResponse> handleRequestNotPermitted(RequestNotPermitted ex) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(ErrorResponse
                        .builder()
                        .error(AppConstant.Exception.REQUEST_NOT_PERMITTED)
                        .debugMessage(ex.getLocalizedMessage()).build());
    }

    // todo modify error response
    @ExceptionHandler(RequestNotPermittedException.class)
    public ResponseEntity<Object> handleRequestNotPermittedException(RequestNotPermittedException ex, HttpServletRequest request) {
        LogUtil.logError(log, String.format("Request to path %s is blocked due to rate-limiting | %s", request.getRequestURI(), ex.getMessage()));
        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Too many requests");
    }

    // todo - modify error response
    @ExceptionHandler(BucketRetrievalFailedException.class)
    public ResponseEntity<Object> handleBucketRetrievalFailedException(BucketRetrievalFailedException ex, HttpServletRequest request) {
        LogUtil.logError(log, String.format("Request path %s is not associated with and rate limit configuration | %s", request.getRequestURI(), ex.getMessage()));
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Rate limit configuration not found");
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse
                        .builder()
                        .error(AppConstant.Exception.RUNTIME_EXCEPTION)
                        .debugMessage(ex.getLocalizedMessage()).build());
    }
}

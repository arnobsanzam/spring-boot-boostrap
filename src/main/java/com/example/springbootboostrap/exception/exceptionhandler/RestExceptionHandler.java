package com.example.springbootboostrap.exception.exceptionhandler;

import com.example.springbootboostrap.constant.AppConstant;
import com.example.springbootboostrap.dto.response.error.ErrorResponse;
import com.example.springbootboostrap.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice(basePackages = {"com.example.springbootboostrap"})
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse
                        .builder()
                        .error(AppConstant.Exception.ENTITY_NOT_FOUND)
                        .debugMessage(ex.getLocalizedMessage()).build());
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

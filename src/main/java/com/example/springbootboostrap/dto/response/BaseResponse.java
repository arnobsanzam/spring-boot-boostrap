package com.example.springbootboostrap.dto.response;

import com.example.springbootboostrap.appenum.StatusType;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class BaseResponse {

    private HttpStatus httpStatus;

    private StatusType requestStatus = StatusType.FAILED;

    private String requestId;

    private Date requestTime;

    private Long processingTime;

    private Date responseTime;

    public BaseResponse() {

    }
}

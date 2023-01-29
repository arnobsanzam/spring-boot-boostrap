package com.example.springbootboostrap.dto.response;

import com.example.springbootboostrap.appenum.StatusType;
import lombok.Data;

import java.util.Date;

@Data
public class BaseResponse {

    private String requestId;
    private StatusType status;
    private Date requestTime;
    private Long processingTime;
    private Date responseTime;
    private String error;

    public BaseResponse() {

    }
}

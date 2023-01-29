package com.example.springbootboostrap.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class BaseRequest {

    private String requestId;

    private Date requestTime;

    public BaseRequest() {

    }
}

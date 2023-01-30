package com.example.springbootboostrap.dto.response.error;

import com.example.springbootboostrap.dto.response.BaseResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse extends BaseResponse {

    private String error;

    private String debugMessage;
}

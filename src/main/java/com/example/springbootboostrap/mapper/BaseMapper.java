package com.example.springbootboostrap.mapper;

import com.example.springbootboostrap.appenum.StatusType;
import com.example.springbootboostrap.dto.response.BaseResponse;
import org.springframework.stereotype.Component;

@Component
public class BaseMapper {

    public <T extends BaseResponse> T toErrorResponse(T response, Exception ex) {
        response.setStatus(StatusType.FAILED);
        response.setError(ex.getMessage());
        return response;
    }
}

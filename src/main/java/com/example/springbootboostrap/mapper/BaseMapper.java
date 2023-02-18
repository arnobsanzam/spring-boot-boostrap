package com.example.springbootboostrap.mapper;

import com.example.springbootboostrap.enums.StatusType;
import com.example.springbootboostrap.dto.response.BaseResponse;


public class BaseMapper {

    public <T extends BaseResponse> T toErrorResponse(T response, Exception ex) {
        response.setRequestStatus(StatusType.FAILED);
        return response;
    }

}

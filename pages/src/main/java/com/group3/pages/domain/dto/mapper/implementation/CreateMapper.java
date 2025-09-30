package com.group3.pages.domain.dto.mapper.implementation;

import com.group3.pages.domain.dto.request.CreatePageReq;

import java.util.Map;

public class CreateMapper {
    
    public CreatePageReq toRequest(String token, Map<String, Object> payload){
        return CreatePageReq.create(
            token,
            (String) payload.get("name"),
            (String) payload.get("base64Image")
        );
    }
    
}

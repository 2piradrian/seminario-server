package com.group3.page_profiles.domain.dto.mapper.implementation;

import com.group3.entity.PageProfile;
import com.group3.page_profiles.domain.dto.request.CreatePageReq;
import com.group3.page_profiles.domain.dto.response.CreatePageRes;

import java.util.Map;

public class CreateMapper {

    public CreatePageReq toRequest(String token, Map<String, Object> payload){
        return CreatePageReq.create(
            token,
            (String) payload.get("name"),
            (String) payload.get("pageTypeId")
        );
    }

    public CreatePageRes toResponse(PageProfile page){
        return new CreatePageRes(
                page.getId()
        );
    }

}

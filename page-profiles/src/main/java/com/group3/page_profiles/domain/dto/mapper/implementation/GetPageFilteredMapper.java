package com.group3.page_profiles.domain.dto.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.PageContent;
import com.group3.entity.PageProfile;
import com.group3.page_profiles.domain.dto.request.GetPageProfilePageFilteredReq;
import com.group3.page_profiles.domain.dto.response.GetPageProfilePageFilteredRes;

import java.util.List;
import java.util.Map;

public class GetPageFilteredMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetPageProfilePageFilteredReq toRequest(String token, Map<String, Object> payload) {
        return GetPageProfilePageFilteredReq.create(
            token,
            (String) payload.get("secret"),
            (Integer) payload.get("page"),
            (Integer) payload.get("size"),
            (String) payload.get("name"),
            (String) payload.get("pageTypeId"),
            objectMapper.convertValue(payload.get("memberIds"), new TypeReference<List<String>>() {})
        );
    }

    public GetPageProfilePageFilteredRes toResponse(PageContent<PageProfile> pages) {
        return new GetPageProfilePageFilteredRes(
            pages.getContent(),
            pages.getNextPage()
        );
    }

}

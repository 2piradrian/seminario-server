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

    public GetPageProfilePageFilteredReq toRequest(String secret, Integer page, Integer size, String name, String pageTypeId) {
        return GetPageProfilePageFilteredReq.create(
            secret,
            page,
            size,
            name,
            pageTypeId
        );
    }

    public GetPageProfilePageFilteredRes toResponse(PageContent<PageProfile> pages) {
        return new GetPageProfilePageFilteredRes(
            pages.getContent(),
            pages.getNextPage()
        );
    }

}

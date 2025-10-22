package com.group3.page_profiles.domain.dto.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.PageProfile;
import com.group3.page_profiles.domain.dto.request.GetPageListByIdsReq;
import com.group3.page_profiles.domain.dto.response.GetPageByIdRes;
import com.group3.page_profiles.domain.dto.response.GetPageListByIdsRes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetListByIdsMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetPageListByIdsReq toRequest(Map<String, Object> payload) {
        return GetPageListByIdsReq.create(
                objectMapper.convertValue(payload.get("pageIds"), new TypeReference<List<String>>() {}),
                (String) payload.get("secret")
        );
    }

    public GetPageListByIdsRes toResponse(List<PageProfile> pages) {
        return new GetPageListByIdsRes(
                pages
        );
    }

}

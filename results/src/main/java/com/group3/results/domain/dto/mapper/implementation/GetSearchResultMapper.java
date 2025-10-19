package com.group3.results.domain.dto.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.*;
import com.group3.results.domain.dto.request.GetSerchResultFilteredReq;
import com.group3.results.domain.dto.response.GetSearchResultFilteredRes;

import java.util.List;
import java.util.Map;

public class GetSearchResultMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetSerchResultFilteredReq toRequest(String token, Map<String, Object> payload) {
        return GetSerchResultFilteredReq.create(
            token,
            (Integer) payload.get("page"),
            (Integer) payload.get("size"),
            (String) payload.get("name"),
            objectMapper.convertValue(payload.get("styles"), new TypeReference<List<Style>>() {}),
            objectMapper.convertValue(payload.get("instruments"), new TypeReference<List<Instrument>>() {}),
            (String) payload.get("contentTypeId"),
            (String) payload.get("pageTypeId")
        );
    }

    public GetSearchResultFilteredRes toResponse(List<UserProfile> userProfiles, List<PageProfile> pageProfiles, List<Post> posts) {
        return new GetSearchResultFilteredRes(
            userProfiles,
            pageProfiles,
            posts
        );
    }

}

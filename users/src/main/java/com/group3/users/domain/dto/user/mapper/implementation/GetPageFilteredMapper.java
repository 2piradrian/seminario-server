package com.group3.users.domain.dto.user.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.PageContent;
import com.group3.entity.User;
import com.group3.users.domain.dto.user.request.GetUserPageFilteredReq;
import com.group3.users.domain.dto.user.response.GetUserPageFilteredRes;

import java.util.List;
import java.util.Map;

public class GetPageFilteredMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetUserPageFilteredReq toRequest(Map<String, Object> payload) {
        return GetUserPageFilteredReq.create(
            (String) payload.get("secret"),
            (Integer) payload.get("page"),
            (Integer) payload.get("size"),
            (String) payload.get("fullname"),
            objectMapper.convertValue(payload.get("styles"), new TypeReference<List<String>>() {}),
            objectMapper.convertValue(payload.get("instruments"), new TypeReference<List<String>>() {})
        );
    }

    public GetUserPageFilteredRes toResponse(PageContent<User> profiles) {
        return new GetUserPageFilteredRes(
            profiles.getContent(),
            profiles.getNextPage()
        );
    }

}

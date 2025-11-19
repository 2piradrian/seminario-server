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

    public GetUserPageFilteredReq toRequest(String secret, Integer page, Integer size, String fullname, List<String> styles, List<String> instruments) {
        return GetUserPageFilteredReq.create(
            secret,
            page,
            size,
            fullname,
            styles,
            instruments
        );
    }

    public GetUserPageFilteredRes toResponse(PageContent<User> profiles) {
        return new GetUserPageFilteredRes(
            profiles.getContent(),
            profiles.getNextPage()
        );
    }

}

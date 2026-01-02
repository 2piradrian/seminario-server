package com.group3.users.domain.dto.user.mapper.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.PageContent;
import com.group3.entity.User;
import com.group3.users.domain.dto.user.request.GetByListOfIdsPageReq;
import com.group3.users.domain.dto.user.response.GetByListOfIdsPageRes;
import com.group3.users.domain.dto.user.response.GetUserPageFilteredRes;

import java.util.List;

public class GetByListOfIdsPageMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetByListOfIdsPageReq toRequest(String token, String secret, Integer page, Integer size, List<String> ids) {
        return GetByListOfIdsPageReq.create(
                token,
                secret,
                page,
                size,
                ids
        );
    }

    public GetByListOfIdsPageRes toResponse(PageContent<User> users) {
        return new GetByListOfIdsPageRes(
                users.getContent(),
                users.getNextPage()
        );
    }

}

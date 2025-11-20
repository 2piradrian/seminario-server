package com.group3.results.domain.dto.mapper.implementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group3.entity.*;
import com.group3.results.domain.dto.request.GetSerchResultFilteredReq;
import com.group3.results.domain.dto.response.GetSearchResultFilteredRes;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class GetSearchResultMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetSerchResultFilteredReq toRequest(String token, Integer page, Integer size, String text, List<String> styles, List<String> instruments, String contentTypeId, String pageTypeId, String postTypeId, String dateInit, String dateEnd) {
        return GetSerchResultFilteredReq.create(
            token,
            page,
            size,
            text,
            styles,
            instruments,
            contentTypeId,
            pageTypeId,
            postTypeId,
            objectMapper.convertValue(dateInit, Date.class),
            objectMapper.convertValue(dateEnd, Date.class)
        );
    }

    public GetSearchResultFilteredRes toResponse(
        List<User> user,
        List<PageProfile> pageProfiles,
        List<Post> posts,
        List<Event> events
    ) {
        return new GetSearchResultFilteredRes(
            user,
            pageProfiles,
            posts,
            events
        );
    }

}

package com.group3.results.domain.dto.mapper.implementation;

import com.group3.results.domain.dto.request.GetFeedMergedByProfileIdPageReq;
import com.group3.results.domain.dto.response.GetFeedMergedByProfileIdPageRes;

import java.time.LocalDateTime;
import java.util.List;

public class GetFeedMergedMapper {

    public GetFeedMergedByProfileIdPageReq toRequest(String token, Integer page, Integer size) {
        return GetFeedMergedByProfileIdPageReq.create(
            token,
            page,
            size
        );
    }

    public GetFeedMergedByProfileIdPageRes toResponse(List<Object> feedContent) {
        return new GetFeedMergedByProfileIdPageRes(
            feedContent
        );
    }

}

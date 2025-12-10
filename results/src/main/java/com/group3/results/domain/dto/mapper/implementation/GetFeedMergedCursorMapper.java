package com.group3.results.domain.dto.mapper.implementation;

import com.group3.entity.CursorContent;
import com.group3.results.domain.dto.request.GetFeedMergedCursorPageReq;
import com.group3.results.domain.dto.response.GetFeedMergedCursorPageRes;

import java.time.LocalDateTime;
import java.util.List;

public class GetFeedMergedCursorMapper {

    public GetFeedMergedCursorPageReq toRequest(String token, String profileId, LocalDateTime cursor, Integer size) {
        return GetFeedMergedCursorPageReq.create(
            token,
            profileId,
            cursor,
            size
        );
    }

    public GetFeedMergedCursorPageRes toResponse(List<Object> feedContent, LocalDateTime nextCursor) {
        return new GetFeedMergedCursorPageRes(
            feedContent,
            nextCursor
        );
    }

}

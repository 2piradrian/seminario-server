package com.group3.results.domain.dto.mapper.implementation;

import com.group3.entity.TimeReportContent;
import com.group3.results.domain.dto.request.GetReportReq;
import com.group3.results.domain.dto.response.GetReportRes;

import java.util.List;

public class GetReportMapper {

    public GetReportReq toRequest(String token) {
        return GetReportReq.create(
            token
        );
    }

    public GetReportRes toResponse(
        TimeReportContent users,
        TimeReportContent posts,
        TimeReportContent events,
        TimeReportContent pages
    ) {
        return new GetReportRes(
            users,
            posts,
            pages,
            events
        );
    }
}

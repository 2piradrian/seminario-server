package com.group3.results.domain.dto.response;

import com.group3.entity.TimeReportContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetReportRes {

    private TimeReportContent users;

    private TimeReportContent posts;

    private TimeReportContent pages;

    private TimeReportContent events;

}

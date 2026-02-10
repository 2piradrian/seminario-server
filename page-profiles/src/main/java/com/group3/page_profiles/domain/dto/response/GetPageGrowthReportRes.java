package com.group3.page_profiles.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPageGrowthReportRes {
    private final Long yearlyCreatedPages;
    private final Long weeklyCreatedPages;
    private final Long monthlyCreatedPages;
}
package com.group3.results.data.datasource.pages_server.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPageGrowthReportRes {
    private Long yearlyCreatedPages;
    private Long monthlyCreatedPages;
    private Long weeklyCreatedPages;
}

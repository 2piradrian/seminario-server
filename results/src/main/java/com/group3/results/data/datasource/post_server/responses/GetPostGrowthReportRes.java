package com.group3.results.data.datasource.post_server.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPostGrowthReportRes {
    private Long yearlyCreatedPosts;
    private Long monthlyCreatedPosts;
    private Long weeklyCreatedPosts;
}

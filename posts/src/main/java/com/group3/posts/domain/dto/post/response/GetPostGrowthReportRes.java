package com.group3.posts.domain.dto.post.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPostGrowthReportRes {
    private final Long yearlyCreatedPosts;
    private final Long weeklyCreatedPosts;
    private final Long monthlyCreatedPosts;
}
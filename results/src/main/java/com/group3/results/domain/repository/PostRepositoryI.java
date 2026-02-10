package com.group3.results.domain.repository;

import com.group3.entity.Post;
import com.group3.entity.TimeReportContent;

import java.util.List;

public interface PostRepositoryI {

    List<Post> getFilteredPosts(String token, Integer page, Integer size, String text, String postTypeId, String secret);

    List<Post> getOnlyPagePosts(String token, String secret, Integer page, Integer size);

    TimeReportContent getGrowthReport(String token, String secret);

}

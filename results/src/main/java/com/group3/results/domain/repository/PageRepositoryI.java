package com.group3.results.domain.repository;

import com.group3.entity.PageProfile;
import com.group3.entity.TimeReportContent;
import com.group3.entity.UserProfile;

import java.util.List;

public interface PageRepositoryI {

    List<PageProfile> getPageFilteredPage(String token, String name, String pageTypeId, Integer page, Integer size, String secret);

    PageProfile getById(String pageId, String token);

    List<PageProfile> getListByIds(List<String> ids, String secret);

    TimeReportContent getGrowthReport(String token, String secret);

}

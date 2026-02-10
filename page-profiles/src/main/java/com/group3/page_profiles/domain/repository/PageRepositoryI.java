package com.group3.page_profiles.domain.repository;

import com.group3.entity.PageContent;
import com.group3.entity.PageProfile;
import com.group3.entity.TimeReportContent;

import java.time.LocalDateTime;
import java.util.List;


public interface PageRepositoryI {

    PageProfile getById(String pageId);

    List<PageProfile> getByUserId(String name);

    PageContent<PageProfile> getFilteredPage(String name, String pageTypeId, Integer page, Integer size);

    PageProfile getByName(String name);

    PageProfile save(PageProfile page);

    PageProfile update(PageProfile page);

    List<PageProfile> getListByIds(List<String> ids);

    void delete(String pageId);

    void deleteByOwnerId(String ownerId);

    void removeMemberFromAllPages(String userId);

    TimeReportContent getGrowthReport(LocalDateTime lastYear, LocalDateTime lastMonth, LocalDateTime lastWeek);
    
}

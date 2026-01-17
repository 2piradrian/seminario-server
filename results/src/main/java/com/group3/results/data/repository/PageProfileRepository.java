package com.group3.results.data.repository;

import com.group3.entity.PageProfile;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.results.data.datasource.pages_server.repository.PageProfilesServerRepositoryI;
import com.group3.results.data.datasource.pages_server.responses.GetPageByIdRes;
import com.group3.results.data.datasource.pages_server.responses.GetPageListByIdsRes;
import com.group3.results.data.datasource.pages_server.responses.GetPageProfilePageFilteredRes;
import com.group3.results.domain.repository.PageRepositoryI;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PageProfileRepository implements PageRepositoryI {

    private final PageProfilesServerRepositoryI repository;

    public List<PageProfile> getPageFilteredPage(String token, String name, String pageTypeId, Integer page, Integer size, String secret){

        GetPageProfilePageFilteredRes response = repository.getPageProfileFilteredPage(token, secret, page, size, name, pageTypeId);

        return response.getPages();
    }

    // ======== Get Page by ID ========

    @Override
    public PageProfile getById(String id, String token) {
        try {
            GetPageByIdRes response = this.repository.getById(token, id);

            PageProfile page = new PageProfile();
            page.setId(response.getId());
            page.setName(response.getName());
            page.setPortraitImage(response.getPortraitImage());
            page.setProfileImage(response.getProfileImage());
            page.setShortDescription(response.getShortDescription());
            page.setLongDescription(response.getLongDescription());
            page.setOwner(response.getOwner());
            page.setMembers(response.getMembers());
            page.setPageType(response.getPageType());

            return page;

        } catch (FeignException e) {
            return null;
        }
    }

    @Override
    public List<PageProfile> getListByIds(List<String> ids, String secret) {
        GetPageListByIdsRes response = this.repository.getListByIds(ids, secret);
        List<PageProfile> pages = new ArrayList<>();

        if (response != null && response.getPages() != null) {
            for (PageProfile r : response.getPages()) {
                PageProfile page = new PageProfile();
                page.setId(r.getId());
                page.setName(r.getName());
                page.setPortraitImage(r.getPortraitImage());
                page.setProfileImage(r.getProfileImage());
                page.setShortDescription(r.getShortDescription());
                page.setLongDescription(r.getLongDescription());
                page.setOwner(r.getOwner());
                page.setMembers(r.getMembers());
                page.setPageType(r.getPageType());
                pages.add(page);
            }
        }
        return pages;
    }

}

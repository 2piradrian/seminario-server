package com.group3.results.data.repository;

import com.group3.entity.PageProfile;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.results.data.datasource.pages_server.repository.PageProfilesServerRepositoryI;
import com.group3.results.data.datasource.pages_server.responses.GetPageByIdRes;
import com.group3.results.data.datasource.pages_server.responses.GetPageProfilePageFilteredRes;
import com.group3.results.domain.repository.PageRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

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
        GetPageByIdRes response = this.repository.getById(token, id);

        if (response == null){
            throw new ErrorHandler(ErrorType.PAGE_NOT_FOUND);
        }

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
    }

}

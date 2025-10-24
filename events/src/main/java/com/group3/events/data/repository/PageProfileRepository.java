package com.group3.events.data.repository;

import com.group3.entity.PageProfile;
import com.group3.events.data.datasource.page_profiles_server.repository.PageProfilesServerRepositoryI;
import com.group3.events.data.datasource.page_profiles_server.responses.GetPageByIdRes;
import com.group3.events.domain.repository.PagesRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PageProfileRepository implements PagesRepositoryI {

    private final PageProfilesServerRepositoryI repository;


    // ======== Get Page by ID ========

    @Override
    public PageProfile getById(String id, String token) {
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
    }

}

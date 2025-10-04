package com.group3.posts.data.repository;

import com.group3.entity.Page;
import com.group3.posts.data.datasource.pages_server.repository.PagesServerRepositoryI;
import com.group3.posts.data.datasource.pages_server.responses.GetPageByIdRes;
import com.group3.posts.domain.repository.PagesRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PagesRepository implements PagesRepositoryI {

    private final PagesServerRepositoryI repository;

    @Override
    public Page getById(String id) {
        GetPageByIdRes response = this.repository.getById(id);

        Page page = new Page();
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

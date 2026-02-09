package com.group3.users.data.repository;

import com.group3.entity.PageProfile;
import com.group3.users.data.datasource.page_profiles_server.repository.PageProfilesServerRepositoryI;
import com.group3.users.data.datasource.page_profiles_server.responses.GetPageByIdRes;
import com.group3.users.domain.repository.PageProfileRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PageProfileRepository implements PageProfileRepositoryI {

    private final PageProfilesServerRepositoryI repository;


    // ======== Single Page Retrieval ========

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


    // ======== Multiple Pages Retrieval ========

    @Override
    public List<PageProfile> getListByIds(List<String> pageIds, String secret) {
        return this.repository.getListByIds(pageIds,secret).getPages();
    }

    @Override
    public void delete(String id, String token) {
        this.repository.delete(token, id);
    }

}

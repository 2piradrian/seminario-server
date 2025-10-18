package com.group3.results.data.repository;

import com.group3.entity.PageProfile;
import com.group3.entity.User;
import com.group3.entity.UserProfile;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.results.data.datasource.pages_server.repository.PageProfilesServerRepositoryI;
import com.group3.results.data.datasource.pages_server.responses.GetPageByIdRes;
import com.group3.results.data.datasource.pages_server.responses.GetPageProfilePageFilteredRes;
import com.group3.results.data.datasource.user_profiles_server.responses.GetUserProfileByIdRes;
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

    public List<PageProfile> getPageFilteredPage(String name, String pageTypeId, List<String> memberIds, Integer page, Integer size, String secret){

        Map<String,Object> payload = new HashMap<>();

        payload.put("name", name);
        payload.put("pageTypeId", pageTypeId);
        payload.put("memberIds", memberIds);
        payload.put("page",page);
        payload.put("size",size);
        payload.put("secret",secret);

        GetPageProfilePageFilteredRes response = repository.getPageProfileFilteredPage(payload);

        return response.getPages();
    }

    // ======== Get Page by ID ========

    @Override
    public PageProfile getById(String id) {
        GetPageByIdRes response = this.repository.getById(id);

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

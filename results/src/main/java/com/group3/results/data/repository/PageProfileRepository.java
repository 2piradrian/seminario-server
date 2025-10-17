package com.group3.results.data.repository;

import com.group3.entity.PageProfile;
import com.group3.results.data.datasource.pages_server.repository.PageProfilesServerRepositoryI;
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

    public List<PageProfile> getPageFilteredPage(String token, String name, String pageTypeId, List<String> memberIds, Integer page, Integer size, String secret){
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

}

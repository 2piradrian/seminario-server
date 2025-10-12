package com.group3.user_profiles.data.repository;

import com.group3.entity.PageProfile;
import com.group3.user_profiles.data.datasource.page_profiles_server.repository.PageProfilesServerRepositoryI;
import com.group3.user_profiles.domain.repository.PageProfileRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PageProfileRepository implements PageProfileRepositoryI {

    private final PageProfilesServerRepositoryI repository;

    @Override
    public List<PageProfile> getListByIds(List<String> pageIds, String secret) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("pageIds", pageIds);
        payload.put("secret", secret);

        return this.repository.getListByIds(payload).getPages();
    }

}

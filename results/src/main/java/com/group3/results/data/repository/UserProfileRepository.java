package com.group3.results.data.repository;

import com.group3.entity.PageContent;
import com.group3.entity.UserProfile;
import com.group3.results.data.datasource.profiles_server.repository.UserProfilesServerRepositoryI;
import com.group3.results.data.datasource.profiles_server.responses.GetUserProfilePageByFullnameRes;
import com.group3.results.domain.repository.ProfileRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class UserProfileRepository implements ProfileRepositoryI {

    private final UserProfilesServerRepositoryI repository;

    public List<UserProfile> getFilteredPage(String fullname, List<String> styles, List<String> instruments, List<String> ids, Integer page, Integer size){

        Map<String,Object> payload = new HashMap<>();

        payload.put("page",page);
        payload.put("size",size);
        payload.put("fullname",fullname);
        payload.put("styles", styles);
        payload.put("instruments", instruments);
        payload.put("ids",ids);

        GetUserProfilePageByFullnameRes response = repository.findByFilteredPage(payload);

        return response.getProfiles();
    }
}

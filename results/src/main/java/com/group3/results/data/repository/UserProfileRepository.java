package com.group3.results.data.repository;

import com.group3.entity.UserProfile;
import com.group3.results.data.datasource.profiles_server.repository.UserProfilesServerRepositoryI;
import com.group3.results.data.datasource.profiles_server.responses.GetUserProfilePageFilteredRes;
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

    public List<UserProfile> getUserFilteredPage(String fullname, List<String> styles, List<String> instruments, List<String> ids, Integer page, Integer size, String secret){
        Map<String,Object> payload = new HashMap<>();

        payload.put("fullname",fullname);
        payload.put("styles", styles);
        payload.put("instruments", instruments);
        payload.put("page",page);
        payload.put("size",size);
        payload.put("ids",ids);
        payload.put("secret",secret);

        GetUserProfilePageFilteredRes response = repository.getUserProfileFilteredPage(payload);

        return response.getProfiles();
    }
}

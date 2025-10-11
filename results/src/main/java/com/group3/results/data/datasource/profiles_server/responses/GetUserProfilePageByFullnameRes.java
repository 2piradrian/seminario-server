package com.group3.results.data.datasource.profiles_server.responses;

import com.group3.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetUserProfilePageByFullnameRes {

    private final List<UserProfile> profiles;

    private final Integer nextPage;

}

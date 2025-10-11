package com.group3.profiles.domain.dto.profile.response;

import com.group3.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetFollowersPageRes {

    List<UserProfile> followers;

    Integer nextPage;
}

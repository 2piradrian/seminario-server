package com.group3.user_profiles.domain.dto.profile.response;

import com.group3.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetFollowerPageRes {

    private final List<Object> followers;

    private final Integer nextPage;

}

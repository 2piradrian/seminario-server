package com.group3.user_profiles.domain.dto.profile.response;

import com.group3.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@Setter
public class GetFollowerPageRes {

    List<Object> followers;

    Integer nextPage;

}

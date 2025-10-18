package com.group3.user_profiles.domain.dto.profile.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class GetFollowerPageRes {

    private final List<Map<String, Object>> followers;

    private final Integer nextPage;

}

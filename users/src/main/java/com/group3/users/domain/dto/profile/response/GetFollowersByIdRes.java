package com.group3.users.domain.dto.profile.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetFollowersByIdRes {

    private final Integer followersCount;

}

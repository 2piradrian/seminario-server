package com.group3.users.domain.dto.profile.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetFollowerPageRes {

    private final List<Object> followers;

    private final Integer nextPage;

}

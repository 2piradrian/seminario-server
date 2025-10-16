package com.group3.results.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetProfilesFilteredRes {

    private final List<Object> profiles;

    private final Integer nextPage;

}

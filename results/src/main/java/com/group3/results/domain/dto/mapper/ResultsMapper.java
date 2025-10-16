package com.group3.results.domain.dto.mapper;

import com.group3.results.domain.dto.mapper.implementation.GetProfilesMapper;

public class ResultsMapper {

    public static GetProfilesMapper getProfilesFiltered() {
        return new GetProfilesMapper();
    }

}

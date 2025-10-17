package com.group3.results.domain.dto.mapper;

import com.group3.results.domain.dto.mapper.implementation.GetSearchResultMapper;

public class ResultsMapper {

    public static GetSearchResultMapper getProfilesFiltered() {
        return new GetSearchResultMapper();
    }

}

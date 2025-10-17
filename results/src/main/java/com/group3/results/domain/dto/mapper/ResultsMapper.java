package com.group3.results.domain.dto.mapper;

import com.group3.results.domain.dto.mapper.implementation.GetFeedMapper;
import com.group3.results.domain.dto.mapper.implementation.GetSearchResultMapper;

public class ResultsMapper {

    public static GetSearchResultMapper getProfilesFiltered() {
        return new GetSearchResultMapper();
    }

    public static GetFeedMapper getFeed(){
        return new GetFeedMapper();
    }
}

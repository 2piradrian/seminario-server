package com.group3.results.domain.dto.mapper;

import com.group3.results.domain.dto.mapper.implementation.GetFeedMapper;
import com.group3.results.domain.dto.mapper.implementation.GetFeedMergedMapper;
import com.group3.results.domain.dto.mapper.implementation.GetReportMapper;
import com.group3.results.domain.dto.mapper.implementation.GetSearchResultMapper;

public class ResultsMapper {

    public static GetSearchResultMapper getSearchResult() {
        return new GetSearchResultMapper();
    }

    public static GetFeedMapper getFeed(){
        return new GetFeedMapper();
    }

    public static GetFeedMergedMapper getFeedMerged(){
        return new GetFeedMergedMapper();
    }

    public static GetReportMapper getReport(){
        return new GetReportMapper();
    }

}

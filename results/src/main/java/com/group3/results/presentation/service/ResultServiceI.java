package com.group3.results.presentation.service;

import com.group3.results.domain.dto.request.GetFeedMergedCursorPageReq;
import com.group3.results.domain.dto.request.GetFeedPageReq;
import com.group3.results.domain.dto.request.GetSerchResultFilteredReq;
import com.group3.results.domain.dto.response.GetFeedMergedCursorPageRes;
import com.group3.results.domain.dto.response.GetFeedPageRes;
import com.group3.results.domain.dto.response.GetSearchResultFilteredRes;

public interface ResultServiceI {

    GetSearchResultFilteredRes getSearchResult(GetSerchResultFilteredReq dto);

    GetFeedPageRes getFeedPage(GetFeedPageReq dto);

    GetFeedMergedCursorPageRes getMergedFeedPage(GetFeedMergedCursorPageReq dto);
}

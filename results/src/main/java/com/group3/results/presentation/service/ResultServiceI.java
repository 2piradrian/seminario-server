package com.group3.results.presentation.service;

import com.group3.results.domain.dto.request.GetFeedPageReq;
import com.group3.results.domain.dto.request.GetProfilesFilteredReq;
import com.group3.results.domain.dto.response.GetFeedPageRes;
import com.group3.results.domain.dto.response.GetProfilesFilteredRes;

public interface ResultServiceI {

    GetProfilesFilteredRes getProfilesFiltered(GetProfilesFilteredReq dto);

    GetFeedPageRes getFeedPage(GetFeedPageReq dto);

}

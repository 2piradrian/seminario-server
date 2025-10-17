package com.group3.results.presentation.service;

import com.group3.results.domain.dto.request.GetSerchResultFilteredReq;
import com.group3.results.domain.dto.response.GetSearchResultFilteredRes;

public interface ResultServiceI {

    GetSearchResultFilteredRes getProfilesFiltered(GetSerchResultFilteredReq dto);

}

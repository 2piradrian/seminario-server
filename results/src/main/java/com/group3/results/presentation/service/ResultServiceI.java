package com.group3.results.presentation.service;

import com.group3.results.domain.dto.request.GetProfilesFilteredReq;
import com.group3.results.domain.dto.response.GetProfilesFilteredRes;

public interface ResultServiceI {

    GetProfilesFilteredRes getProfilesFiltered(GetProfilesFilteredReq dto);

}

package com.group3.page_profiles.domain.dto.response;

import com.group3.entity.PageProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetPageProfilePageFilteredRes {

    private final List<PageProfile> pages;

    private final Integer nextPage;

}

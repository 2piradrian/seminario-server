package com.group3.results.data.datasource.pages_profiles.responses;

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

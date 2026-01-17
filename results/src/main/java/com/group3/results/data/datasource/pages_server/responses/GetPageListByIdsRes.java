package com.group3.results.data.datasource.pages_server.responses;

import com.group3.entity.PageProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetPageListByIdsRes {

    private final List<PageProfile> pages;

}

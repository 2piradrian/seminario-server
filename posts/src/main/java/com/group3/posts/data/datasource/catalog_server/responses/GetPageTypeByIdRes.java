package com.group3.posts.data.datasource.catalog_server.responses;

import com.group3.entity.PageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPageTypeByIdRes {

    private final PageType pageType;

}

package com.group3.pages.data.datasource.catalog_server.responses;

import com.group3.entity.PageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetPageTypeByIdRes {

    private final PageType pageType;

}

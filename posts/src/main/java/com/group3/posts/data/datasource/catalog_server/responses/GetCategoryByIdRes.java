package com.group3.posts.data.datasource.catalog_server.responses;

import com.group3.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetCategoryByIdRes {

    private final Category category;

}
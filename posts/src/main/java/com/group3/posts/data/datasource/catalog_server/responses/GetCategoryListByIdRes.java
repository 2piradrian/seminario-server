package com.group3.posts.data.datasource.catalog_server.responses;

import com.group3.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetCategoryListByIdRes {

    List<Category> categories;

}


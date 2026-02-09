package com.group3.users.data.datasource.catalog_server.responses.category;

import com.group3.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryListByIdRes {

    private List<Category> categories;

}

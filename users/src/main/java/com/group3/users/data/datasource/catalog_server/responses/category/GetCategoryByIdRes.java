package com.group3.users.data.datasource.catalog_server.responses.category;

import com.group3.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryByIdRes {

    private Category category;

}

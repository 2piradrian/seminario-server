package com.group3.catalog.domain.dto.category.response;

import com.group3.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllCategoryRes {

    List<Category> categories;

}

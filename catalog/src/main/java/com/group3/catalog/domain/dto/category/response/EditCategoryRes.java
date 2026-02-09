package com.group3.catalog.domain.dto.category.response;

import com.group3.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EditCategoryRes {
    private final Category category;
}

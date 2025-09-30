package com.group3.catalog.data.postgres.mapper;

import com.group3.catalog.data.postgres.model.CategoryModel;
import com.group3.entity.Category;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryEntityMapper {

    public static Category toDomain(CategoryModel model ){
        if (model == null) return null;

        return new Category(
                model.getId(),
                model.getName()
        );
    }

    public static CategoryModel toModel(Category categories) {
        if (categories == null) return null;

        return new CategoryModel(
                categories.getId(),
                categories.getName()
        );
    }

    public static List<Category> toDomain(List<CategoryModel> models) {
        if (models == null) return Collections.emptyList();

        return models.stream()
                .map(CategoryEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static List<CategoryModel> toModel(List<Category> categories) {
        if (categories == null) return Collections.emptyList();

        return categories.stream()
                .map(CategoryEntityMapper::toModel)
                .collect(Collectors.toList());
    }
}

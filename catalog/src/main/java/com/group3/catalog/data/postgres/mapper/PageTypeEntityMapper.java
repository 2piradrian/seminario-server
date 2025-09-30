package com.group3.catalog.data.postgres.mapper;

import com.group3.catalog.data.postgres.model.PageTypeModel;
import com.group3.entity.PageType;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PageTypeEntityMapper {

    public static PageType toDomain(PageTypeModel model ){
        if (model == null) return null;

        return new PageType(
                model.getId(),
                model.getName()
        );
    }

    public static PageTypeModel toModel(PageType pageTypes) {
        if (pageTypes == null) return null;

        return new PageTypeModel(
                pageTypes.getId(),
                pageTypes.getName()
        );
    }

    public static List<PageType> toDomain(List<PageTypeModel> models) {
        if (models == null) return Collections.emptyList();

        return models.stream()
                .map(PageTypeEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static List<PageTypeModel> toModel(List<PageType> pageTypes) {
        if (pageTypes == null) return Collections.emptyList();

        return pageTypes.stream()
                .map(PageTypeEntityMapper::toModel)
                .collect(Collectors.toList());
    }
}

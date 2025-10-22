package com.group3.catalog.data.datasource.postgres.mapper;

import com.group3.catalog.data.datasource.postgres.model.ContentTypeModel;
import com.group3.entity.ContentType;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ContentTypeEntityMapper {

    public static ContentType toDomain(ContentTypeModel model) {
        if (model == null) return null;

        return new ContentType(
            model.getId(),
            model.getName()
        );
    }

    public static ContentTypeModel toModel(ContentType contentType) {
        if (contentType == null) return null;

        return new ContentTypeModel(
            contentType.getId(),
            contentType.getName()
        );
    }

    public static List<ContentType> toDomain(List<ContentTypeModel> models) {
        if (models == null) return Collections.emptyList();

        return models.stream()
                .map(ContentTypeEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static List<ContentTypeModel> toModel(List<ContentType> contentTypes) {
        if (contentTypes == null) return Collections.emptyList();

        return contentTypes.stream()
                .map(ContentTypeEntityMapper::toModel)
                .collect(Collectors.toList());
    }

}

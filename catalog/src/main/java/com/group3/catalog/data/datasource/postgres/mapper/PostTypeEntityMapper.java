package com.group3.catalog.data.datasource.postgres.mapper;

import com.group3.catalog.data.datasource.postgres.model.PostTypeModel;
import com.group3.entity.PostType;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PostTypeEntityMapper {

    public static PostType toDomain(PostTypeModel model){
        if (model == null) return null;

        return new PostType(
                model.getId(),
                model.getName()
        );
    }

    public static PostTypeModel toModel(PostType postTypes) {
        if (postTypes == null) return null;

        return new PostTypeModel(
                postTypes.getId(),
                postTypes.getName()
        );
    }

    public static List<PostType> toDomain(List<PostTypeModel> models) {
        if (models == null) return Collections.emptyList();

        return models.stream()
                .map(PostTypeEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static List<PostTypeModel> toModel(List<PostType> postTypes) {
        if (postTypes == null) return Collections.emptyList();

        return postTypes.stream()
                .map(PostTypeEntityMapper::toModel)
                .collect(Collectors.toList());
    }
}

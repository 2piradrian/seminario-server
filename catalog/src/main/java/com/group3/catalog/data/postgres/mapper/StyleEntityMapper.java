package com.group3.catalog.data.postgres.mapper;

import com.group3.catalog.data.postgres.model.StyleModel;
import com.group3.entity.Style;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StyleEntityMapper {

    public static Style toDomain(StyleModel model) {
        if (model == null) return null;

        return new Style(
            model.getId(),
            model.getName()
        );
    }

    public static StyleModel toModel(Style style) {
        if (style == null) return null;

        return new StyleModel(
            style.getId(),
            style.getName()
        );
    }

    public static List<Style> toDomain(List<StyleModel> models) {
        if (models == null) return Collections.emptyList();

        return models.stream()
                .map(StyleEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static List<StyleModel> toModel(List<Style> styles) {
        if (styles == null) return Collections.emptyList();

        return styles.stream()
                .map(StyleEntityMapper::toModel)
                .collect(Collectors.toList());
    }

}

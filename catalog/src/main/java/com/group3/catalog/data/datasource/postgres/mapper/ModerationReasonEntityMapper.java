package com.group3.catalog.data.datasource.postgres.mapper;

import com.group3.catalog.data.datasource.postgres.model.ModerationReasonModel;
import com.group3.entity.ModerationReason;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ModerationReasonEntityMapper {

    public static ModerationReason toDomain(ModerationReasonModel model ){
        if (model == null) return null;

        return new ModerationReason(
                model.getId(),
                model.getName()
        );
    }

    public static ModerationReasonModel toModel(ModerationReason domain) {
        if (domain == null) return null;

        return new ModerationReasonModel(
                domain.getId(),
                domain.getName()
        );
    }

    public static List<ModerationReason> toDomain(List<ModerationReasonModel> models) {
        if (models == null) return Collections.emptyList();

        return models.stream()
                .map(ModerationReasonEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static List<ModerationReasonModel> toModel(List<ModerationReason> domains) {
        if (domains == null) return Collections.emptyList();

        return domains.stream()
                .map(ModerationReasonEntityMapper::toModel)
                .collect(Collectors.toList());
    }
}

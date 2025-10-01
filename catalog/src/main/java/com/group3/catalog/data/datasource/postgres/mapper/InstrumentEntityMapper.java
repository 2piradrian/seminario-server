package com.group3.catalog.data.datasource.postgres.mapper;

import com.group3.catalog.data.datasource.postgres.model.InstrumentModel;
import com.group3.entity.Instrument;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InstrumentEntityMapper {

    public static Instrument toDomain(InstrumentModel model) {
        if (model == null) return null;

        return new Instrument(
                model.getId(),
                model.getName()
        );
    }

    public static InstrumentModel toModel(Instrument instrument) {
        if (instrument == null) return null;

        return new InstrumentModel(
                instrument.getId(),
                instrument.getName()
        );
    }

    public static List<Instrument> toDomain(List<InstrumentModel> models) {
        if (models == null) return Collections.emptyList();

        return models.stream()
                .map(InstrumentEntityMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static List<InstrumentModel> toModel(List<Instrument> instruments) {
        if (instruments == null) return Collections.emptyList();

        return instruments.stream()
                .map(InstrumentEntityMapper::toModel)
                .collect(Collectors.toList());
    }

}

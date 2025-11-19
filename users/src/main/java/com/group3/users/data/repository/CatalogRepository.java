package com.group3.users.data.repository;

import com.group3.entity.Instrument;
import com.group3.entity.Style;
import com.group3.users.data.datasource.catalog_server.repository.CatalogServerRepositoryI;
import com.group3.users.domain.repository.CatalogRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CatalogRepository implements CatalogRepositoryI {

    private final CatalogServerRepositoryI repository;


    // ======== Styles ========

    @Override
    public List<Style> getAllStyle() {
        List<Style> styles = this.repository.getAllStyle().getStyles();
        return styles == null ? List.of() : styles;
    }

    @Override
    public Style getStyleById(String styleId) {
        return this.repository.getStyleById(styleId).getStyle();
    }

    @Override
    public List<Style> getStyleListById(List<String> styles) {
        return this.repository.getStyleListById(styles).getStyles();
    }


    // ======== Instruments ========

    @Override
    public List<Instrument> getAllInstrument() {
        List<Instrument> instruments = this.repository.getAllInstrument().getInstruments();
        return instruments != null ? instruments : List.of();
    }

    @Override
    public Instrument getInstrumentById(String instrumentId) {
        return this.repository.getInstrumentById(instrumentId).getInstrument();
    }

    @Override
    public List<Instrument> getInstrumentListById(List<String> instruments) {
        return this.repository.getInstrumentListById(instruments).getInstruments();
    }

}

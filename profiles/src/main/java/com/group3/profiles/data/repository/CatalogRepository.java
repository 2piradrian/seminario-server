package com.group3.profiles.data.repository;

import com.group3.entity.Instrument;
import com.group3.entity.Style;
import com.group3.profiles.data.catalog_server.repository.CatalogServerRepositoryI;
import com.group3.profiles.domain.repository.CatalogRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CatalogRepository implements CatalogRepositoryI {

    private final CatalogServerRepositoryI catalogRepository;

    @Override
    public List<Style> getAllStyle() {
        List<Style> styles = this.catalogRepository.getAllStyle().getStyles();

        if (styles == null){
            return List.of();
        }

        return styles;
    }

    @Override
    public Style getStyleById(String styleId) {
        return this.catalogRepository.getStyleById(styleId).getStyle();
    }

    @Override
    public List<Style> getStyleListById(List<String> styles) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("ids", styles);
        return this.catalogRepository.getStyleListById(payload).getStyles();
    }

    @Override
    public List<Instrument> getAllInstrument() {
        List<Instrument> instruments = this.catalogRepository.getAllInstrument().getInstruments();
        return instruments != null ? instruments : List.of();
    }

    @Override
    public Instrument getInstrumentById(String instrumentId) {
        return this.catalogRepository.getInstrumentById(instrumentId).getInstrument();
    }

    @Override
    public List<Instrument> getInstrumentListById(List<String> instruments) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("ids", instruments);
        List<Instrument> response = this.catalogRepository.getInstrumentListById(payload).getInstruments();
        return response != null ? response : List.of();
    }

}

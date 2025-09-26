package com.group3.profiles.domain.repository;

import com.group3.entity.Instrument;
import com.group3.entity.Style;

import java.util.List;


public interface CatalogRepositoryI {

    List<Style> getAllStyle();

    Style getStyleById(String styleId);

    List<Style> getStyleListById(List<String> styles);

    List<Instrument> getAllInstrument();

    Instrument getInstrumentById(String instrumentId);

    List<Instrument> getInstrumentListById(List<String> instruments);

}

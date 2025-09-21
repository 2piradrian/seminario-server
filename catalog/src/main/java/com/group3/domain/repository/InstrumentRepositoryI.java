package com.group3.domain.repository;

import com.group3.entity.Instrument;
import com.group3.entity.Style;

import java.util.List;

public interface InstrumentRepositoryI {

    Instrument getById(String instrumentId);

    List<Instrument> getAll();

    Instrument save(Instrument instrument);

    Instrument update(Instrument instrument);

}

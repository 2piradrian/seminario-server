package com.group3.catalog.data.repository;

import com.group3.catalog.data.datasource.postgres.mapper.InstrumentEntityMapper;
import com.group3.catalog.data.datasource.postgres.model.InstrumentModel;
import com.group3.catalog.data.datasource.postgres.repository.PostgresInstrumentRepositoryI;
import com.group3.catalog.domain.repository.InstrumentRepositoryI;
import com.group3.entity.Instrument;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class InstrumentRepository implements InstrumentRepositoryI {

    private final PostgresInstrumentRepositoryI instrumentRepository;

    @Override
    public Instrument getById(String instrumentId) {
        InstrumentModel model = this.instrumentRepository.findById(instrumentId).orElse(null);
        return model != null ? InstrumentEntityMapper.toDomain(model) : null;
    }

    @Override
    public List<Instrument> getAll() {
        List<InstrumentModel> models = this.instrumentRepository.findAll();
        return InstrumentEntityMapper.toDomain(models);
    }

    @Override
    public Instrument save(Instrument instrument) {
        InstrumentModel model = InstrumentEntityMapper.toModel(instrument);
        InstrumentModel saved = this.instrumentRepository.save(model);
        return InstrumentEntityMapper.toDomain(saved);
    }

    @Override
    public Instrument update(Instrument instrument) {
        InstrumentModel model = InstrumentEntityMapper.toModel(instrument);
        InstrumentModel updated = this.instrumentRepository.save(model);
        return InstrumentEntityMapper.toDomain(updated);
    }

    @Override
    public void delete(String instrumentId) {
        this.instrumentRepository.deleteById(instrumentId);
    }

}

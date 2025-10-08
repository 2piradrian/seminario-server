package com.group3.catalog.data.datasource.postgres.repository;

import com.group3.catalog.data.datasource.postgres.model.InstrumentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresInstrumentRepositoryI extends JpaRepository<InstrumentModel, String> {

}

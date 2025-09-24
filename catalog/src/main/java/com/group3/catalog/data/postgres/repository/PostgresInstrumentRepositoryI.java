package com.group3.catalog.data.postgres.repository;

import com.group3.catalog.data.postgres.model.InstrumentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresInstrumentRepositoryI extends JpaRepository<InstrumentModel, String> {

}

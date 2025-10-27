package com.group3.events.data.datasource.postgres.repository;

import com.group3.events.data.datasource.postgres.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresEventRepositoryI extends JpaRepository<EventModel, String> {
}

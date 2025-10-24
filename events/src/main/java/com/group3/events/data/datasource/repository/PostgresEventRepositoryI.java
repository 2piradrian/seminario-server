package com.group3.events.data.datasource.repository;

import com.group3.events.data.datasource.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresEventRepositoryI extends JpaRepository<EventModel, String> {
}

package com.group3.catalog.data.datasource.postgres.repository;

import com.group3.catalog.data.datasource.postgres.model.ModerationReasonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresModerationReasonRepositoryI extends JpaRepository<ModerationReasonModel, String> {
}

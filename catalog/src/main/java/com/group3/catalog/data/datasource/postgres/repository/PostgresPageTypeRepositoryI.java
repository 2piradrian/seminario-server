package com.group3.catalog.data.datasource.postgres.repository;

import com.group3.catalog.data.datasource.postgres.model.PageTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresPageTypeRepositoryI extends JpaRepository<PageTypeModel, String> {

}

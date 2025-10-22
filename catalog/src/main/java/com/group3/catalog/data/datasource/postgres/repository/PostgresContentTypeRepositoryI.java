package com.group3.catalog.data.datasource.postgres.repository;

import com.group3.catalog.data.datasource.postgres.model.ContentTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresContentTypeRepositoryI extends JpaRepository<ContentTypeModel, String> {

}

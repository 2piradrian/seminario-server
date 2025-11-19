package com.group3.catalog.data.datasource.postgres.repository;

import com.group3.catalog.data.datasource.postgres.model.PostTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresPostTypeRepositoryI extends JpaRepository<PostTypeModel, String> {

}

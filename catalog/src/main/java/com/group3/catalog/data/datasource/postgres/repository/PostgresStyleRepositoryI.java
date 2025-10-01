package com.group3.catalog.data.datasource.postgres.repository;

import com.group3.catalog.data.datasource.postgres.model.StyleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresStyleRepositoryI extends JpaRepository<StyleModel, String> {

}

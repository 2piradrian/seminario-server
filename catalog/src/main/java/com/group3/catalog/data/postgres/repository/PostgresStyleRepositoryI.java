package com.group3.catalog.data.postgres.repository;

import com.group3.catalog.data.postgres.model.StyleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresStyleRepositoryI extends JpaRepository<StyleModel, String> {

}

package com.group3.catalog.data.datasource.postgres.repository;

import com.group3.catalog.data.datasource.postgres.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresCategoryRepositoryI extends JpaRepository<CategoryModel, String>{
}

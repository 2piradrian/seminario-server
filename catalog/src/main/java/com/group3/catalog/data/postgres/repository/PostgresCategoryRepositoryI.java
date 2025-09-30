package com.group3.catalog.data.postgres.repository;

import com.group3.catalog.data.postgres.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresCategoryRepositoryI extends JpaRepository<CategoryModel, String>{
}

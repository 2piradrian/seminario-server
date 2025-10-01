package com.group3.catalog.data.repository;

import com.group3.catalog.data.datasource.postgres.mapper.CategoryEntityMapper;
import com.group3.catalog.data.datasource.postgres.model.CategoryModel;
import com.group3.catalog.data.datasource.postgres.repository.PostgresCategoryRepositoryI;
import com.group3.catalog.domain.repository.CategoryRepositoryI;
import com.group3.entity.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CategoryRepository implements CategoryRepositoryI {

    private final PostgresCategoryRepositoryI categoryRepository;

    @Override
    public Category getById(String categoryId){
        CategoryModel model = this.categoryRepository.findById(categoryId).orElse(null);
        return model != null ? CategoryEntityMapper.toDomain(model) : null;
    }

    @Override
    public List<Category> getAll() {
        List<CategoryModel> models = this.categoryRepository.findAll();
        return CategoryEntityMapper.toDomain(models);
    }

    @Override
    public Category save(Category category) {
        CategoryModel model = CategoryEntityMapper.toModel(category);
        CategoryModel saved = this.categoryRepository.save(model);
        return CategoryEntityMapper.toDomain(saved);
    }

    @Override
    public Category update(Category category) {
        CategoryModel model = CategoryEntityMapper.toModel(category);
        CategoryModel updated = this.categoryRepository.save(model);
        return CategoryEntityMapper.toDomain(updated);
    }

}

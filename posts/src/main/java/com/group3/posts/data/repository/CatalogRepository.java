package com.group3.posts.data.repository;

import com.group3.entity.PageType;
import com.group3.entity.Category;
import com.group3.posts.data.datasource.catalog_server.repository.CatalogServerRepositoryI;
import com.group3.posts.domain.repository.CatalogRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CatalogRepository implements CatalogRepositoryI {

    private final CatalogServerRepositoryI repository;

    @Override
    public List<Category> getAllCategory() {
        List<Category> styles = this.repository.getAllCategory().getCategories();

        return styles == null ? List.of() : styles;
    }

    @Override
    public Category getCategoryById(String categoryId) {
        return this.repository.getCategoryById(categoryId).getCategory();
    }

    @Override
    public List<Category> getCategoryListById(List<String> categories) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("ids", categories);
        return this.repository.getCategoryListById(payload).getCategories();
    }

}

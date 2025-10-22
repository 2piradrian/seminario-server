package com.group3.posts.data.repository;

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


    // ======== Get All Categories ========
    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = this.repository.getAllCategory().getCategories();
        return categories == null ? List.of() : categories;
    }


    // ======== Get Category by ID ========
    @Override
    public Category getCategoryById(String categoryId) {
        return this.repository.getCategoryById(categoryId).getCategory();
    }


    // ======== Get List of Categories by IDs ========
    @Override
    public List<Category> getCategoryListById(List<String> categoryIds) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("ids", categoryIds);
        return this.repository.getCategoryListById(payload).getCategories();
    }

}

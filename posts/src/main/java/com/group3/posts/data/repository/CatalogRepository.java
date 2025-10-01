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
    public Category getCategoryById(String styleId) {
        return this.repository.getCategoryById(styleId).getCategory();
    }

    @Override
    public List<Category> getCategoryListById(List<String> styles) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("ids", styles);
        return this.repository.getCategoryListById(payload).getCategories();
    }

    @Override
    public List<PageType> getAllPageType() {
        List<PageType> pageTypes = this.repository.getAllPageType().getPageTypes();
        return pageTypes != null ? pageTypes : List.of();
    }

    @Override
    public PageType getPageTypeById(String instrumentId) {
        return this.repository.getPageTypeById(instrumentId).getPageType();
    }

    @Override
    public List<PageType> getPageTypeListById(List<String> instruments) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("ids", instruments);
        return this.repository.getPageTypeListById(payload).getPageTypes();
    }

}

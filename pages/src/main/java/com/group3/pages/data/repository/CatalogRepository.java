package com.group3.pages.data.repository;

import com.group3.entity.PageType;
import com.group3.pages.data.datasource.catalog_server.repository.CatalogServerRepositoryI;
import com.group3.pages.domain.repository.CatalogRepositoryI;
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
    public List<PageType> getAll() {
        List<PageType> pageTypes = this.repository.getAll().getPageTypes();

        return pageTypes == null ? List.of() : pageTypes;
    }

    @Override
    public PageType getById(String pageTypeId) {
        return this.repository.getById(pageTypeId).getPageType();
    }

    @Override
    public List<PageType> getListById(List<String> pageTypes) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("ids", pageTypes);
        return this.repository.getListById(payload).getPageTypes();
    }

}

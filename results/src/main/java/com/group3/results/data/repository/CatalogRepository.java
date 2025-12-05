package com.group3.results.data.repository;

import com.group3.entity.ContentType;
import com.group3.results.data.datasource.catalog_server.repository.CatalogServerRepositoryI;
import com.group3.results.domain.repository.CatalogRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CatalogRepository implements CatalogRepositoryI {

    private final CatalogServerRepositoryI repository;

    // ======== Get Category by ID ========

    @Override
    public ContentType getContentById(String contentId) {
        return this.repository.getContentTypeById(contentId).getContentType();
    }


}

package com.group3.results.domain.repository;

import com.group3.entity.Category;
import com.group3.entity.ContentType;

import java.util.List;

public interface CatalogRepositoryI {

    ContentType getContentById(String contentId);

}

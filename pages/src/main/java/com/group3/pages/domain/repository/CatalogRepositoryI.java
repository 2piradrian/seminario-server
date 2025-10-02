package com.group3.pages.domain.repository;

import com.group3.entity.PageType;
import com.group3.entity.Style;

import java.util.List;

public interface CatalogRepositoryI {

    List<PageType> getAll();

    PageType getById(String pageTypeId);

    List<Style> getListById(List<String> pageTypeIds);

}

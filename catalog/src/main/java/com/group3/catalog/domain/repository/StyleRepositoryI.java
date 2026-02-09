package com.group3.catalog.domain.repository;

import com.group3.entity.Style;

import java.util.List;

public interface StyleRepositoryI {

    Style getById(String styleId);

    List<Style> getAll();

    Style save(Style style);

    Style update(Style style);

    void delete(String styleId);

}

package com.group3.catalog.presentation.service;

import com.group3.catalog.data.repository.CategoryRepository;
import com.group3.catalog.domain.dto.category.mapper.CategoryMapper;
import com.group3.catalog.domain.dto.category.request.GetCategoryByIdReq;
import com.group3.catalog.domain.dto.category.request.GetCategoryListByIdReq;
import com.group3.catalog.domain.dto.category.response.GetAllCategoryRes;
import com.group3.catalog.domain.dto.category.response.GetCategoryByIdRes;
import com.group3.catalog.domain.dto.category.response.GetCategoryListByIdRes;
import com.group3.entity.Category;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class CategoryService implements CategoryServiceI {

    private final CategoryRepository repository;

    @Override
    public GetAllCategoryRes getAll(){
        List<Category> Category = this.repository.getAll();
        return CategoryMapper.getAll().toResponse(Category);
    }

    @Override
    public GetCategoryByIdRes getById(GetCategoryByIdReq dto) {
        Category Category = this.repository.getById(dto.getId());

        if (Category == null) {
            throw new ErrorHandler(ErrorType.STYLE_NOT_FOUND);
        }

        return CategoryMapper.getById().toResponse(Category);
    }

    @Override
    public GetCategoryListByIdRes getListById(GetCategoryListByIdReq dto) {
        List<Category> Categories = dto.getIds().stream()
                .map(this.repository::getById)
                .filter(Objects::nonNull)
                .toList();

        if (Categories.isEmpty()) {
            return CategoryMapper.getListById().toResponse(List.of());
        }

        return CategoryMapper.getListById().toResponse(Categories);
    }
}

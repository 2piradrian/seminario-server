package com.group3.catalog.presentation.service;

import com.group3.catalog.data.repository.CategoryRepository;
import com.group3.catalog.data.repository.UserRepository;
import com.group3.catalog.domain.dto.category.mapper.CategoryMapper;
import com.group3.catalog.domain.dto.category.request.*;
import com.group3.catalog.domain.dto.category.response.*;
import com.group3.entity.Category;
import com.group3.entity.Role;
import com.group3.entity.User;
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
    private final UserRepository userRepository;

    @Override
    public GetAllCategoryRes getAll(){
        List<Category> category = this.repository.getAll();
        return CategoryMapper.getAll().toResponse(category);
    }

    @Override
    public GetCategoryByIdRes getById(GetCategoryByIdReq dto) {
        Category category = this.repository.getById(dto.getId());

        if (category == null) {
            throw new ErrorHandler(ErrorType.CATEGORY_NOT_FOUND);
        }

        return CategoryMapper.getById().toResponse(category);
    }

    @Override
    public GetCategoryListByIdRes getListById(GetCategoryListByIdReq dto) {
        List<Category> categories = dto.getIds().stream()
                .map(this.repository::getById)
                .filter(Objects::nonNull)
                .toList();

        if (categories.isEmpty()) {
            return CategoryMapper.getListById().toResponse(List.of());
        }

        return CategoryMapper.getListById().toResponse(categories);
    }

    @Override
    public CreateCategoryRes create(CreateCategoryReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        Category category = new Category();
        category.setName(dto.getName());

        Category saved = this.repository.save(category);

        return CategoryMapper.create().toResponse(saved);
    }

    @Override
    public EditCategoryRes edit(EditCategoryReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        Category category = this.repository.getById(dto.getId());

        if (category == null) {
            throw new ErrorHandler(ErrorType.CATEGORY_NOT_FOUND);
        }

        category.setName(dto.getName());

        Category updated = this.repository.update(category);

        return CategoryMapper.edit().toResponse(updated);
    }

    @Override
    public void delete(DeleteCategoryReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        Category category = this.repository.getById(dto.getId());

        if (category == null) {
            throw new ErrorHandler(ErrorType.CATEGORY_NOT_FOUND);
        }

        this.repository.delete(category.getId());
    }
}

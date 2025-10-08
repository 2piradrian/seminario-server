package com.group3.catalog.presentation.controller;

import com.group3.catalog.domain.dto.category.mapper.CategoryMapper;
import com.group3.catalog.domain.dto.category.request.GetCategoryByIdReq;
import com.group3.catalog.domain.dto.category.request.GetCategoryListByIdReq;
import com.group3.catalog.presentation.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping("/get-by-id/{categoryId}")
    public ResponseEntity<?> getById(
            @PathVariable(value = "categoryId") String categoryId
    ){
        GetCategoryByIdReq dto = CategoryMapper.getById().toRequest(categoryId);

        return ResponseEntity.ok(this.service.getById(dto));
    }

    @PostMapping("/get-list-by-id")
    public ResponseEntity<?> getListById(
            @RequestBody Map<String, Object> payload
    ){
        GetCategoryListByIdReq dto = CategoryMapper.getListById().toRequest(payload);

        return ResponseEntity.ok(this.service.getListById(dto));
    }
}

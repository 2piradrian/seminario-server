package com.group3.catalog.presentation.controller;

import com.group3.catalog.domain.dto.category.mapper.CategoryMapper;
import com.group3.catalog.domain.dto.category.request.*;
import com.group3.catalog.presentation.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
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

    @GetMapping("/get-list-by-id")
    public ResponseEntity<?> getListById(
            @RequestParam(value = "ids") List<String> ids
    ){
        GetCategoryListByIdReq dto = GetCategoryListByIdReq.create(ids);

        return ResponseEntity.ok(this.service.getListById(dto));
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        CreateCategoryReq dto = CategoryMapper.create().toRequest(token, payload);

        return ResponseEntity.ok(this.service.create(dto));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> edit(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "categoryId") String categoryId,
            @RequestBody Map<String, Object> payload
    ) {
        EditCategoryReq dto = CategoryMapper.edit().toRequest(token, categoryId, payload);

        return ResponseEntity.ok(this.service.edit(dto));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> delete(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "categoryId") String categoryId
    ) {
        DeleteCategoryReq dto = CategoryMapper.delete().toRequest(token, categoryId);
        this.service.delete(dto);

        return ResponseEntity.ok().build();
    }

}

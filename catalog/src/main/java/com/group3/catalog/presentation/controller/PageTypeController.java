package com.group3.catalog.presentation.controller;

import com.group3.catalog.domain.dto.pagetype.mapper.PageTypeMapper;
import com.group3.catalog.domain.dto.pagetype.request.GetPageTypeByIdReq;
import com.group3.catalog.domain.dto.pagetype.request.GetPageTypeListByIdReq;
import com.group3.catalog.presentation.service.PageTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/page-types")
public class PageTypeController {

    private final PageTypeService service;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping("/get-by-id/{pageTypeId}")
    public ResponseEntity<?> getById(
            @PathVariable(value = "pageTypeId") String pageTypeId
    ){
        GetPageTypeByIdReq dto = PageTypeMapper.getById().toRequest(pageTypeId);

        return ResponseEntity.ok(this.service.getById(dto));
    }

    @GetMapping("/get-list-by-id")
    public ResponseEntity<?> getListById(
            @RequestParam(value = "ids") List<String> ids
    ){
        GetPageTypeListByIdReq dto = GetPageTypeListByIdReq.create(ids);

        return ResponseEntity.ok(this.service.getListById(dto));
    }
}

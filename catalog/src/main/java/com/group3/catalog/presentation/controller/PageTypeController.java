package com.group3.catalog.presentation.controller;

import com.group3.catalog.domain.dto.pagetype.mapper.PageTypeMapper;
import com.group3.catalog.domain.dto.pagetype.request.*;
import com.group3.catalog.presentation.service.PageTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping
    public ResponseEntity<?> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        CreatePageTypeReq dto = PageTypeMapper.create().toRequest(token, payload);

        return ResponseEntity.ok(this.service.create(dto));
    }

    @PutMapping("/{pageTypeId}")
    public ResponseEntity<?> edit(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "pageTypeId") String pageTypeId,
            @RequestBody Map<String, Object> payload
    ) {
        EditPageTypeReq dto = PageTypeMapper.edit().toRequest(token, pageTypeId, payload);

        return ResponseEntity.ok(this.service.edit(dto));
    }

    @DeleteMapping("/{pageTypeId}")
    public ResponseEntity<?> delete(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "pageTypeId") String pageTypeId
    ) {
        DeletePageTypeReq dto = PageTypeMapper.delete().toRequest(token, pageTypeId);
        this.service.delete(dto);

        return ResponseEntity.ok().build();
    }
}

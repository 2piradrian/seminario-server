package com.group3.catalog.presentation.controller;

import com.group3.catalog.domain.dto.pagetype.mapper.PageTypeMapper;
import com.group3.catalog.domain.dto.pagetype.request.GetPageTypeByIdReq;
import com.group3.catalog.domain.dto.pagetype.request.GetPageTypeListByIdReq;
import com.group3.catalog.presentation.service.PageTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pageTypes")
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

    @PostMapping("/get-list-by-id")
    public ResponseEntity<?> getListById(
            @RequestBody Map<String, Object> payload
    ){
        GetPageTypeListByIdReq dto = PageTypeMapper.getListById().toRequest(payload);

        return ResponseEntity.ok(this.service.getListById(dto));
    }
}

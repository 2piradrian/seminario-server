package com.group3.catalog.presentation.controller;

import com.group3.catalog.domain.dto.contentType.mapper.ContentTypeMapper;
import com.group3.catalog.domain.dto.contentType.request.GetContentTypeByIdReq;
import com.group3.catalog.domain.dto.contentType.request.GetContentTypeListByIdReq;
import com.group3.catalog.presentation.service.ContentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/content-types")
public class ContentTypeController {

    private final ContentTypeService service;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping("/get-by-id/{contentTypeId}")
    public ResponseEntity<?> getById(
        @PathVariable(value = "contentTypeId") String contentTypeId
    ){
        GetContentTypeByIdReq dto = ContentTypeMapper.getById().toRequest(contentTypeId);

        return ResponseEntity.ok(this.service.getById(dto));
    }

    @PostMapping("/get-list-by-id")
    public ResponseEntity<?> getListById(
        @RequestBody Map<String, Object> payload
    ) {
        GetContentTypeListByIdReq dto = ContentTypeMapper.getListById().toRequest(payload);
        
        return ResponseEntity.ok(this.service.getListById(dto));
    }

}

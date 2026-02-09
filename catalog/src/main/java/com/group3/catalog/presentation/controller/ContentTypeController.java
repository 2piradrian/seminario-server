package com.group3.catalog.presentation.controller;

import com.group3.catalog.domain.dto.contentType.mapper.ContentTypeMapper;
import com.group3.catalog.domain.dto.contentType.request.*;
import com.group3.catalog.presentation.service.ContentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/get-list-by-id")
    public ResponseEntity<?> getListById(
        @RequestParam(value = "ids") List<String> ids
    ) {
        GetContentTypeListByIdReq dto = GetContentTypeListByIdReq.create(ids);
        
        return ResponseEntity.ok(this.service.getListById(dto));
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        CreateContentTypeReq dto = ContentTypeMapper.create().toRequest(token, payload);

        return ResponseEntity.ok(this.service.create(dto));
    }

    @PutMapping("/{contentTypeId}")
    public ResponseEntity<?> edit(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "contentTypeId") String contentTypeId,
            @RequestBody Map<String, Object> payload
    ) {
        EditContentTypeReq dto = ContentTypeMapper.edit().toRequest(token, contentTypeId, payload);

        return ResponseEntity.ok(this.service.edit(dto));
    }

    @DeleteMapping("/{contentTypeId}")
    public ResponseEntity<?> delete(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "contentTypeId") String contentTypeId
    ) {
        DeleteContentTypeReq dto = ContentTypeMapper.delete().toRequest(token, contentTypeId);
        this.service.delete(dto);

        return ResponseEntity.ok().build();
    }

}

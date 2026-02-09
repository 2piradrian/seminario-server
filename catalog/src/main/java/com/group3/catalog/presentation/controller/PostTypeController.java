package com.group3.catalog.presentation.controller;

import com.group3.catalog.domain.dto.posttype.mapper.PostTypeMapper;
import com.group3.catalog.domain.dto.posttype.request.*;
import com.group3.catalog.presentation.service.PostTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/post-types")
public class PostTypeController {

    private final PostTypeService service;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping("/get-by-id/{postTypeId}")
    public ResponseEntity<?> getById(
            @PathVariable(value = "postTypeId") String postTypeId
    ){
        GetPostTypeByIdReq dto = PostTypeMapper.getById().toRequest(postTypeId);

        return ResponseEntity.ok(this.service.getById(dto));
    }

    @PostMapping("/get-list-by-id")
    public ResponseEntity<?> getListById(
            @RequestBody Map<String, Object> payload
    ){
        GetPostTypeListByIdReq dto = PostTypeMapper.getListById().toRequest(payload);

        return ResponseEntity.ok(this.service.getListById(dto));
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        CreatePostTypeReq dto = PostTypeMapper.create().toRequest(token, payload);

        return ResponseEntity.ok(this.service.create(dto));
    }

    @PutMapping("/{postTypeId}")
    public ResponseEntity<?> edit(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "postTypeId") String postTypeId,
            @RequestBody Map<String, Object> payload
    ) {
        EditPostTypeReq dto = PostTypeMapper.edit().toRequest(token, postTypeId, payload);

        return ResponseEntity.ok(this.service.edit(dto));
    }

    @DeleteMapping("/{postTypeId}")
    public ResponseEntity<?> delete(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "postTypeId") String postTypeId
    ) {
        DeletePostTypeReq dto = PostTypeMapper.delete().toRequest(token, postTypeId);
        this.service.delete(dto);

        return ResponseEntity.ok().build();
    }
}

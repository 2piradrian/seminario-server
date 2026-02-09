package com.group3.catalog.presentation.controller;

import com.group3.catalog.domain.dto.style.mapper.StyleMapper;
import com.group3.catalog.domain.dto.style.request.*;
import com.group3.catalog.presentation.service.StyleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/styles")
public class StyleController {

    private final StyleService service;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping("/get-by-id/{styleId}")
    public ResponseEntity<?> getById(
        @PathVariable(value = "styleId") String styleId
    ){
        GetStyleByIdReq dto = StyleMapper.getById().toRequest(styleId);

        return ResponseEntity.ok(this.service.getById(dto));
    }

    @GetMapping("/get-list-by-id")
    public ResponseEntity<?> getListById(
        @RequestParam(value = "ids") List<String> ids
    ) {
        GetStyleListByIdReq dto = GetStyleListByIdReq.create(ids);
        
        return ResponseEntity.ok(this.service.getListById(dto));
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        CreateStyleReq dto = StyleMapper.create().toRequest(token, payload);

        return ResponseEntity.ok(this.service.create(dto));
    }

    @PutMapping("/{styleId}")
    public ResponseEntity<?> edit(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "styleId") String styleId,
            @RequestBody Map<String, Object> payload
    ) {
        EditStyleReq dto = StyleMapper.edit().toRequest(token, styleId, payload);

        return ResponseEntity.ok(this.service.edit(dto));
    }

    @DeleteMapping("/{styleId}")
    public ResponseEntity<?> delete(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "styleId") String styleId
    ) {
        DeleteStyleReq dto = StyleMapper.delete().toRequest(token, styleId);
        this.service.delete(dto);

        return ResponseEntity.ok().build();
    }

}

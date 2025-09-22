package com.group3.catalog.presentation.controller;

import com.group3.catalog.domain.dto.style.mapper.StyleMapper;
import com.group3.catalog.domain.dto.style.request.GetStyleByIdReq;
import com.group3.catalog.domain.dto.style.request.GetStyleListByIdReq;
import com.group3.catalog.presentation.service.StyleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/instruments")
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

    @PostMapping("/get-list-by-id")
    public ResponseEntity<?> getListById(
        @RequestBody Map<String, Object> payload
    ) {
        GetStyleListByIdReq dto = StyleMapper.getListById().toRequest(payload);
        
        return ResponseEntity.ok(this.service.getListById(dto));
    }

}

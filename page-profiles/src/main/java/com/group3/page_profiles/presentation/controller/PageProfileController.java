package com.group3.page_profiles.presentation.controller;

import com.group3.page_profiles.domain.dto.mapper.PageMapper;
import com.group3.page_profiles.domain.dto.request.*;
import com.group3.page_profiles.presentation.service.PageProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/page-profiles")
public class PageProfileController {
    
    private final PageProfileService pageService;

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ){
        CreatePageReq dto = PageMapper.create().toRequest(token, payload);

        return ResponseEntity.ok(this.pageService.create(dto));
    }

    @GetMapping("/get-by-id/{pageId}")
    public ResponseEntity<?> getById(
        @RequestHeader(value = "Authorization") String token,
        @PathVariable(value = "pageId") String pageId
    ) {
        GetPageByIdReq dto = PageMapper.getPage().toRequest(token, pageId);

        return ResponseEntity.ok(this.pageService.getById(dto));
    }

    @PostMapping("/get-page-filtered")
    public ResponseEntity<?> getFiltered(
        @RequestBody Map<String, Object> payload
    ) {
        GetPageProfilePageFilteredReq dto = PageMapper.getFiltered().toRequest(payload);
        return ResponseEntity.ok(this.pageService.getProfileFiltered(dto));
    }

    @GetMapping("/get-by-user-id/{userId}")
    public ResponseEntity<?> getOwnProfile(
        @RequestHeader(value = "Authorization") String token,
        @PathVariable(value = "userId") String userId
    ) {
        GetPageByUserIdReq dto = PageMapper.getUserPages().toRequest(token, userId);

        return ResponseEntity.ok(this.pageService.getUserPages(dto));
    }

    @PostMapping("/get-list-by-id")
    public ResponseEntity<?> getListById(
            @RequestBody Map<String, Object> payload
    ) {
        GetPageListByIdsReq dto = PageMapper.getListByIds().toRequest(payload);
        return ResponseEntity.ok(this.pageService.getListByIds(dto));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(
        @RequestHeader(value = "Authorization") String token,
        @RequestBody Map<String, Object> payload
    ) {
        EditPageReq dto = PageMapper.edit().toRequest(token, payload);
        this.pageService.edit(dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(
        @RequestHeader(value = "Authorization") String token,
        @RequestBody Map<String, Object> payload
    ) {
        DeletePageReq dto = PageMapper.delete().toRequest(token, payload);
        this.pageService.delete(dto);

        return ResponseEntity.ok().build();
    }
    
}

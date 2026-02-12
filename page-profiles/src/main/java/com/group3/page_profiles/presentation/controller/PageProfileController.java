package com.group3.page_profiles.presentation.controller;

import com.group3.page_profiles.domain.dto.mapper.PageMapper;
import com.group3.page_profiles.domain.dto.request.*;
import com.group3.page_profiles.presentation.service.PageProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/page-profiles")
public class PageProfileController {
    
    private final PageProfileService pageService;

    @PostMapping
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

    @GetMapping("/get-page-filtered")
    public ResponseEntity<?> getFiltered(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "secret") String secret,
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "pageTypeId", required = false) String pageTypeId
    ) {
        GetPageProfilePageFilteredReq dto = PageMapper.getFiltered().toRequest(token, secret, page, size, name, pageTypeId);
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

    @GetMapping("/get-list-by-id")
    public ResponseEntity<?> getListById(
            @RequestParam(value = "pageIds") List<String> pageIds,
            @RequestParam(value = "secret") String secret
    ) {
        GetPageListByIdsReq dto = PageMapper.getListByIds().toRequest(pageIds, secret);
        return ResponseEntity.ok(this.pageService.getListByIds(dto));
    }

    @PutMapping("/{pageId}")
    public ResponseEntity<?> edit(
        @RequestHeader(value = "Authorization") String token,
        @PathVariable(value = "pageId") String pageId,
        @RequestBody Map<String, Object> payload
    ) {
        EditPageReq dto = PageMapper.edit().toRequest(token, pageId, payload);
        this.pageService.edit(dto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/leave/{pageId}")
    public ResponseEntity<?> leave(
        @RequestHeader(value = "Authorization") String token,
        @PathVariable(value = "pageId") String pageId
    ) {
        LeavePageReq dto = PageMapper.leavePage().toRequest(token, pageId);
        this.pageService.leavePage(dto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/join/{pageId}")
    public ResponseEntity<?> join(
        @RequestHeader(value = "Authorization") String token,
        @PathVariable(value = "pageId") String pageId
    ) {
        JoinPageReq dto = PageMapper.joinPage().toRequest(token, pageId);
        this.pageService.joinPage(dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{pageId}")
    public ResponseEntity<?> delete(
        @RequestHeader(value = "Authorization") String token,
        @PathVariable(value = "pageId") String pageId,
        @RequestBody Map<String, Object> payload
    ) {
        DeletePageReq dto = PageMapper.delete().toRequest(token, pageId, payload);
        this.pageService.delete(dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-user-pages/{userId}")
    public ResponseEntity<?> deleteUserPages(
            @PathVariable(value = "userId") String userId,
            @RequestParam(value = "secret") String secret
    ) {
        this.pageService.deleteUserPages(PageMapper.deleteUserPages().toRequest(userId, secret));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/growth-report")
    public ResponseEntity<?> getGrowthReport(
            @RequestHeader(value = "Authorization") String token,
            @RequestParam(value = "secret") String secret
    ) {
        GetPageGrowthReportReq dto = PageMapper.getPageGrowthReport().toRequest(token, secret);
        return ResponseEntity.ok(this.pageService.getGrowthReport(dto));
    }
    
}

package com.group3.results.presentation.controller;

import com.group3.results.domain.dto.mapper.ResultsMapper;
import com.group3.results.domain.dto.request.GetFeedPageReq;
import com.group3.results.domain.dto.request.GetSerchResultFilteredReq;
import com.group3.results.presentation.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/results")
public class ResultController {

    private final ResultService resultService;

    @GetMapping("/get-search-result")
    public ResponseEntity<?> getSearchResult(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size,
        @RequestParam(value = "text", required = false) String text,
        @RequestParam(value = "styles", required = false) List<String> styles,
        @RequestParam(value = "instruments", required = false) List<String> instruments,
        @RequestParam(value = "contentTypeId") String contentTypeId,
        @RequestParam(value = "pageTypeId", required = false) String pageTypeId,
        @RequestParam(value = "postTypeId", required = false) String postTypeId,
        @RequestParam(value = "dateInit", required = false) String dateInit,
        @RequestParam(value = "dateEnd", required = false) String dateEnd
    ) {
        GetSerchResultFilteredReq dto = ResultsMapper.getSearchResult().toRequest(token, page, size, text, styles, instruments, contentTypeId, pageTypeId, postTypeId, dateInit, dateEnd);
        return ResponseEntity.ok(this.resultService.getSearchResult(dto));
    }

    @GetMapping("/get-feed-post")
    public ResponseEntity<?> getFeedPost(
        @RequestHeader(value = "Authorization") String token,
        @RequestParam(value = "page") Integer page,
        @RequestParam(value = "size") Integer size
    ) {
        GetFeedPageReq dto = ResultsMapper.getFeed().toRequest(page, size, token);
        return ResponseEntity.ok(this.resultService.getFeedPage(dto));
    }

}

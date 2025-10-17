package com.group3.results.presentation.controller;

import com.group3.results.domain.dto.mapper.ResultsMapper;
import com.group3.results.domain.dto.request.GetFeedPageReq;
import com.group3.results.domain.dto.request.GetSerchResultFilteredReq;
import com.group3.results.presentation.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/results")
public class ResultController {

    private final ResultService resultService;

    @PostMapping("/get-search-result-filtered")
    public ResponseEntity<?> getFiltered(
        @RequestHeader(value = "Authorization") String token,
        @RequestBody Map<String, Object> payload
    ) {
        GetSerchResultFilteredReq dto = ResultsMapper.getProfilesFiltered().toRequest(token, payload);
        return ResponseEntity.ok(this.resultService.getProfilesFiltered(dto));
    }

    @PostMapping("/get-feed-post-filtered")
    public ResponseEntity<?> getFeedPost(
        @RequestHeader(value = "Authorization") String token,
        @RequestBody Map<String, Object> payload
    ) {
        GetFeedPageReq dto = ResultsMapper.getFeed().toRequest(token, payload);
        return ResponseEntity.ok(this.resultService.getFeedPage(dto));
    }

}

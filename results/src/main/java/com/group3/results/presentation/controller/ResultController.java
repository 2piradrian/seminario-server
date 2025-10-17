package com.group3.results.presentation.controller;

import com.group3.results.domain.dto.mapper.ResultsMapper;
import com.group3.results.domain.dto.request.GetProfilesFilteredReq;
import com.group3.results.presentation.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/results")
public class ResultController {

    private final ResultService resultService;

    @PostMapping("/get-filtered")
    public ResponseEntity<?> getFiltered(
        @RequestBody Map<String, Object> payload
    ) {
        GetProfilesFilteredReq dto = ResultsMapper.getProfilesFiltered().toRequest(payload);
        return ResponseEntity.ok(this.resultService.getProfilesFiltered(dto));
    }

}

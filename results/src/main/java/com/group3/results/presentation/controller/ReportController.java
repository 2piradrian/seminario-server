package com.group3.results.presentation.controller;

import com.group3.results.domain.dto.mapper.ResultsMapper;
import com.group3.results.domain.dto.request.GetFeedMergedByProfileIdPageReq;
import com.group3.results.domain.dto.request.GetReportReq;
import com.group3.results.domain.dto.response.GetReportRes;
import com.group3.results.presentation.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/get-report")
    public ResponseEntity<GetReportRes> getReport(
            @RequestHeader("Authorization") String token
    ) {
        GetReportReq dto = ResultsMapper.getReport().toRequest(token);
        return ResponseEntity.ok(this.reportService.getReport(dto));
    }
}

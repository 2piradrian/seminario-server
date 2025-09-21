package com.group3.catalog.presentation.controller;

import com.group3.catalog.domain.dto.instrument.mapper.InstrumentMapper;
import com.group3.catalog.domain.dto.instrument.request.GetInstrumentByIdReq;
import com.group3.catalog.domain.dto.instrument.request.GetInstrumentListByIdReq;
import com.group3.catalog.presentation.service.InstrumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/instruments")
public class InstrumentController {

    private final InstrumentService service;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }

    @GetMapping("/get-by-id/{instrumentId}")
    public ResponseEntity<?> getById(
        @PathVariable(value = "instrumentId") String instrumentId
    ){
        GetInstrumentByIdReq dto = InstrumentMapper.getById().toRequest(instrumentId);

        return ResponseEntity.ok(this.service.getById(dto));
    }

    @GetMapping("/get-list-by-id")
    public ResponseEntity<?> getListById(
        @RequestBody Map<String, Object> payload
    ) {
        GetInstrumentListByIdReq dto = InstrumentMapper.getListById().toRequest(payload);

        return ResponseEntity.ok(this.service.getListById(dto));
    }

}

package com.group3.catalog.presentation.controller;

import com.group3.catalog.domain.dto.instrument.mapper.InstrumentMapper;
import com.group3.catalog.domain.dto.instrument.request.*;
import com.group3.catalog.presentation.service.InstrumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        @RequestParam(value = "ids") List<String> ids
    ) {
        GetInstrumentListByIdReq dto = GetInstrumentListByIdReq.create(ids);

        return ResponseEntity.ok(this.service.getListById(dto));
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody Map<String, Object> payload
    ) {
        CreateInstrumentReq dto = InstrumentMapper.create().toRequest(token, payload);

        return ResponseEntity.ok(this.service.create(dto));
    }

    @PutMapping("/{instrumentId}")
    public ResponseEntity<?> edit(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "instrumentId") String instrumentId,
            @RequestBody Map<String, Object> payload
    ) {
        EditInstrumentReq dto = InstrumentMapper.edit().toRequest(token, instrumentId, payload);

        return ResponseEntity.ok(this.service.edit(dto));
    }

    @DeleteMapping("/{instrumentId}")
    public ResponseEntity<?> delete(
            @RequestHeader(value = "Authorization") String token,
            @PathVariable(value = "instrumentId") String instrumentId
    ) {
        DeleteInstrumentReq dto = InstrumentMapper.delete().toRequest(token, instrumentId);
        this.service.delete(dto);

        return ResponseEntity.ok().build();
    }

}

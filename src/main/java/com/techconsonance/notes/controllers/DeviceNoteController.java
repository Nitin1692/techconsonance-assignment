package com.techconsonance.notes.controllers;

import com.techconsonance.notes.dto.*;
import com.techconsonance.notes.services.DeviceNoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/devices/{deviceId}/notes")
@RequiredArgsConstructor
@Slf4j
public class DeviceNoteController {

    private final DeviceNoteService service;

    @PostMapping
    public ResponseEntity<DeviceNoteResponse> createNote(
            @PathVariable Long deviceId,
            @RequestHeader(value = "X-User", required = false) String user,
            @RequestBody CreateNoteRequest req) {

        log.info("Create note request deviceId={} user={}", deviceId, user);

        DeviceNoteResponse response = service.createNote(deviceId, user, req);

        return ResponseEntity
                .status(HttpStatus.CREATED)   // 201
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<DeviceNoteResponse>> listNotes(
            @PathVariable Long deviceId,
            @RequestParam(required = false) Integer limit) {

        log.info("List notes request deviceId={}", deviceId);

        List<DeviceNoteResponse> response = service.listNotes(deviceId, limit);

        return ResponseEntity.ok(response);  // 200
    }
}
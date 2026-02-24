package com.techconsonance.notes.services;

import com.techconsonance.notes.dto.*;
import com.techconsonance.notes.entity.DeviceNote;
import com.techconsonance.notes.exception.BadRequestException;
import com.techconsonance.notes.repository.DeviceNoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceNoteService {

    private final DeviceNoteRepository repo;

    @Transactional
    public DeviceNoteResponse createNote(Long deviceId, String user, CreateNoteRequest req) {

        if (user == null || user.isBlank())
            throw new BadRequestException("Missing X-User header");

        if (req.getNote() == null || req.getNote().isBlank())
            throw new BadRequestException("Note must not be blank");

        if (req.getNote().length() > 1000)
            throw new BadRequestException("Note exceeds 1000 characters");

        DeviceNote note = DeviceNote.builder()
                .deviceId(deviceId)
                .note(req.getNote())
                .createdBy(user)
                .createdAt(LocalDateTime.now())
                .build();

        repo.save(note);

        log.info("Note created id={} deviceId={}", note.getId(), deviceId);

        return map(note);
    }

    public List<DeviceNoteResponse> listNotes(Long deviceId, Integer limit) {

        int lim = (limit == null) ? 20 : limit;

        if (lim < 1 || lim > 100) {
            log.warn("Invalid limit value: {}", lim);
            throw new BadRequestException("Limit must be between 1 and 100");
        }

        Pageable pageable = PageRequest.of(
                0,
                lim,
                Sort.by("createdAt").descending()
        );

        return repo.findByDeviceId(deviceId, pageable)
                .stream()
                .map(this::map)
                .toList();
    }

    private DeviceNoteResponse map(DeviceNote n) {
        return DeviceNoteResponse.builder()
                .id(n.getId())
                .deviceId(n.getDeviceId())
                .note(n.getNote())
                .createdBy(n.getCreatedBy())
                .createdAt(n.getCreatedAt())
                .build();
    }
}
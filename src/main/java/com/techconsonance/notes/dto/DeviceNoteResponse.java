package com.techconsonance.notes.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @Builder
public class DeviceNoteResponse {
    private Long id;
    private Long deviceId;
    private String note;
    private String createdBy;
    private LocalDateTime createdAt;
}
package com.techconsonance.notes.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "device_note")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class DeviceNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="device_id", nullable=false)
    private Long deviceId;

    @Column(nullable=false)
    private String note;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="created_by", nullable=false)
    private String createdBy;
}
package com.techconsonance.notes.repository;

import com.techconsonance.notes.entity.DeviceNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceNoteRepository extends JpaRepository<DeviceNote, Long> {

    Page<DeviceNote> findByDeviceId(Long deviceId, Pageable pageable);
}
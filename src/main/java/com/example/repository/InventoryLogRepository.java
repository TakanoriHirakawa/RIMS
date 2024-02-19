package com.example.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.InventoryLog;

public interface InventoryLogRepository extends JpaRepository<InventoryLog, Integer>{

	Optional<InventoryLog> findByCreationTimeStamp(LocalDateTime ldt);
}

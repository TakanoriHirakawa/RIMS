package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Repairing;

public interface RepairingRepository extends JpaRepository<Repairing,Integer>{
	Optional<Repairing>findById(Integer id);

	Optional<Repairing> findByRepairNo(String repairNo);

}

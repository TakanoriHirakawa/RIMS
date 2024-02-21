package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.UsedItemsReport;

public interface UsedItemsReportRepository extends JpaRepository<UsedItemsReport,Integer>{
}

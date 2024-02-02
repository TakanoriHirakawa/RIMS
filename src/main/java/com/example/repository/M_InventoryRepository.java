package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.M_Inventory;

public interface M_InventoryRepository extends JpaRepository<M_Inventory,Integer> {
	/**
	 * 図番によるレコード1件検索
	 * @param itemNo：図番
	 * */
	Optional<M_Inventory>findByItemNo(String itemNo); 
}

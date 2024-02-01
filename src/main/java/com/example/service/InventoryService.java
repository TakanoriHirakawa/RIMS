package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.M_Inventory;
import com.example.entity.M_Product;
import com.example.form.InventoryForm;
import com.example.repository.M_InventoryRepository;
import com.example.repository.M_ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
	/**m_inventoryテーブルDAO*/
	private final M_InventoryRepository mInventoryRepository;
	
	/**m_productテーブルDAO*/
	private final M_ProductRepository mProductRepository;
	
	/**m_inventoryテーブルから全件取得*/
	public List<M_Inventory> getInventoryList() {
		return mInventoryRepository.findAll();
	}
	
	/**m_productテーブルから全件取得*/
	public List<M_Product> getProductList() {
		return mProductRepository.findAll();
	}
	
	/**
	 * 図番によるレコード1件検索
	 * @param itemNo：図番
	 * @return Optional<M_Inventory>：dbにてitemNoを検索した結果をOptionalで返す
	 * */
	public Optional<M_Inventory> findRecodeByItemNo(String itemNo) {
		 return  mInventoryRepository.findByItemNo(itemNo);
	}

	public void registItem(InventoryForm form) {
		M_Inventory result = new M_Inventory();
		result.setFkProductId(form.getFkProductId());
		result.setItemNo(form.getItemNo());
		result.setItemName(form.getItemName());
		result.setStock(form.getStock());
		result.setUnitPrice(form.getUnitPrice());
		result.setBasicQuantity(form.getBasicQuantity());
		result.setStockMin(form.getStockMin());
		result.setStockMax(form.getStockMax());
		result.setStatusDone(form.getStatusDone());
		result.setStatusUnable(form.getStatusUnable());
		result.setRemarks(form.getRemarks());
		
		System.out.println(result);
		
		mInventoryRepository.save(result);
	}


}

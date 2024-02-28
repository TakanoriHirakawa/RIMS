package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.entity.InventoryLog;
import com.example.entity.M_Inventory;
import com.example.entity.M_Product;
import com.example.entity.M_User;
import com.example.form.InventoryForm;
import com.example.form.RecevingItem;
import com.example.form.TempRecevingItemList;
import com.example.repository.InventoryLogRepository;
import com.example.repository.M_InventoryRepository;
import com.example.repository.M_ProductRepository;
import com.example.repository.M_UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
	
	/**m_userテーブルDAO*/
	private final M_UserRepository mUserRepository;
	
	/**m_inventoryテーブルDAO*/
	private final M_InventoryRepository mInventoryRepository;
	
	/**m_productテーブルDAO*/
	private final M_ProductRepository mProductRepository;
	
	/*inventory_logテーブルDAO*/
	private final InventoryLogRepository inventoryLogRepository;	
	
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
	/**
	 * receiving_items.htmlに引渡すformオブジェクト
	 * TempRecevingItemListを新たに生成する
	 * */
	public TempRecevingItemList createNewRecevingItemsList() {
		return new TempRecevingItemList();
	}
	
	
	/**
	 * 入庫情報の登録メソッド
	 * */
	public void resistRecevingItems(TempRecevingItemList tempRecevingItemList,@AuthenticationPrincipal User user) {
		for (RecevingItem recevingItem : tempRecevingItemList.getRecevingItemList()) {
			if(recevingItem.getItemNo().equals("")) {
				break;
			}
			Optional<M_User> findResult = mUserRepository.findByUserId(user.getUsername());
			String currentUser = findResult.get().getUserName();
			LocalDateTime now = LocalDateTime.now();
			Optional<M_Inventory>itemRecord =mInventoryRepository.findByItemNo(recevingItem.getItemNo());
			
			InventoryLog currentInventoryLog = new InventoryLog();
			currentInventoryLog.setId(null);
			currentInventoryLog.setFkInventoryId(itemRecord.get().getId());
			currentInventoryLog.setFkProcessTypeId(2);//procecssTypeテーブルよりid=2：入庫
			currentInventoryLog.setChangedQuantity(recevingItem.getQuantity());
			currentInventoryLog.setStockAtTheTime(itemRecord.get().getStock()+recevingItem.getQuantity());			
			currentInventoryLog.setAuthor(currentUser);
			currentInventoryLog.setCreationTimeStamp(now);
			
			inventoryLogRepository.save(currentInventoryLog);

			//在庫データの更新
			M_Inventory inventory = itemRecord.get();
			inventory.setStock(currentInventoryLog.getStockAtTheTime());
			inventory.setChanger(currentUser);
			inventory.setUpadateTimeStamp(now);
			
			mInventoryRepository.save(inventory);
		}
		
	}


}

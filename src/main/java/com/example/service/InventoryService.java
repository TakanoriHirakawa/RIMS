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
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
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
	
	/**
	 * DBに新たに物品を登録
	 * @param ：入力内容
	 * */
	public void saveItem(InventoryForm form) {
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

		mInventoryRepository.save(result);
		log.info("新規物品登録 / 図番：" + result.getItemNo());
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
	public void saveRecevingItems(TempRecevingItemList tempRecevingItemList,@AuthenticationPrincipal User user) {
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
	
	/**
	 * 引数の入庫物品リストを初期化
	 * @param tempReceivingItemList
	 * */
	public void renewRecevingItems(TempRecevingItemList tempReceivingItemList) {
		tempReceivingItemList.getRecevingItemList().clear();
		tempReceivingItemList.initializeList(tempReceivingItemList.getRecevingItemList());
	}
	
	/**
	 * 図番の重複チェック
	 * @param itemNo ：図番
	 * @return boolean型の結果（重複ありでtrue）
	 * */
	public boolean isDuplicate(String itemNo) {
		boolean result=false;
		//重複チェック
		Optional<M_Inventory>findResult = mInventoryRepository.findByItemNo(itemNo);
		if(findResult.isPresent()) {
			result=true;
		}	
		return result;
	}
	
	/**
	 * 物品登録時のnull,重複Check
	 * @param form : 入力内容
	 * @return result：結果(Errorあり：true)
	 * */
	public boolean hasErrorInputData(InventoryForm form) {
		boolean hasError = false;
		//null,""チェック
		if (form==null) {
			hasError=true;
		}else if(form.getItemNo()==null || form.getItemNo().equals("")) {
			hasError = true;
		}else if(form.getItemName()==null || form.getItemName().equals("")) {
			hasError = true;
		}
		hasError=isDuplicate(form.getItemNo());
		return hasError;
	}


}

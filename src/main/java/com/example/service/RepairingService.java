package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.entity.InventoryLog;
import com.example.entity.M_Contract;
import com.example.entity.M_Inventory;
import com.example.entity.M_Product;
import com.example.entity.M_User;
import com.example.entity.Repairing;
import com.example.entity.TargetProducts;
import com.example.entity.UsedItemsReport;
import com.example.form.TempRepairingForm;
import com.example.form.TempReports;
import com.example.form.TempUsedItemsReportForm;
import com.example.mapper.TargetProductsMapper;
import com.example.repository.InventoryLogRepository;
import com.example.repository.M_ContractRepository;
import com.example.repository.M_InventoryRepository;
import com.example.repository.M_ProductRepository;
import com.example.repository.M_UserRepository;
import com.example.repository.RepairingRepository;
import com.example.repository.TargetProductsRepository;
import com.example.repository.UsedItemsReportRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RepairingService {

	/**m_userテーブルDAO*/
	private final M_UserRepository mUserRepository;

	/**repairingテーブルDAO*/
	private final RepairingRepository repairingRepository;

	/**m_contractテーブルDAO*/
	private final M_ContractRepository mContractRepository;

	/**m_productテーブルDAO*/
	private final M_ProductRepository mProductRepository;

	/**target_productsテーブルDAO*/
	private final TargetProductsRepository targetProductsRepository;

	/**m_inventoryテーブルDAO*/
	private final M_InventoryRepository mInventoryRepository;
	
	/*inventory_logテーブルDAO*/
	private final InventoryLogRepository inventoryLogRepository;
	
	/*used_items_reportテーブルDAO**/
	private final UsedItemsReportRepository usedItemsReportRepository;

	@Autowired
	private TargetProductsMapper contractProductMapper;

	/**
	 * m_userテーブルから全情報を取得*/
	public List<M_User> getUserList() {
		return mUserRepository.findAll();
	}

	/**m_contractテーブルから全件取得*/
	public List<M_Contract> getContractList() {
		return mContractRepository.findAll();
	}

	/**m_productテーブルから全件取得*/
	public List<M_Product> getProductList() {
		return mProductRepository.findAll();
	}

	/**target_contractテーブルから全権取得*/
	public List<TargetProducts> getTargetProductsList() {
		return targetProductsRepository.findAll();
	}

	/**m_inventoryテーブルから全件取得*/
	public List<M_Inventory> getInventoryList() {
		return mInventoryRepository.findAll();
	}

	/**
	 * target_contractテーブルで
	 * 引数（fk_contract_id）と一致するListを生成
	 * @param m_contract.id=target_products.fk_contract_id
	 * @return List<TargetProducts> 引数(fk_contract_id)でフィルタしたリスト。
	 * */
	public List<M_Product> getListFilteredByContractId(Integer selectedContractId) {
		return contractProductMapper.getListFilteredBySelectedContractId(selectedContractId);
	}

	/**
	 * 引数（id）と一致するレコードのleadTimeを返す。
	 * @param selectedContractId => M_Contract.id
	 * @return M_Contract.leadTime
	 * 引数（id）を含むレコードが存在しない場合は、0を返す。
	 * */
	public Integer getLeadTimeById(Integer selectedContractId) {
		Optional<M_Contract> target = mContractRepository.findById(selectedContractId);
		if (target.isEmpty()) {
			return 0;
		}
		return target.get().getLeadTime();
	}

	/**
	 * 修理報告書および使用部品報告書の入力情報を一時的に保持するインスタンスを生成する。
	 * @return TempReportsコンストラクタ
	 * */
	public TempReports getTempRereports() {
		return new TempReports();
	}

	/**
	 * m_inventoryテーブルから引数のitemNoを持つレコードを一件取得する。
	 * @param itemNo 図番
	 * @return Optional<M_Invenotry> 引数の図番を持つレコードor Optional.empty（）;
	 * */
	public Optional<M_Inventory> findItemByItemNo(String itemNo) {
		return mInventoryRepository.findByItemNo(itemNo);
	}

	/**
	 * m_userテーブルから引数のidをもつレコードを一件取得する。
	 * @param username ：springSecurittyのログインユーザー名
	 * @return M_user.userName
	 * */
	public Integer findIdByAuthUserName(String username) {
		Optional<M_User> findResult = mUserRepository.findByUserId(username);
		return findResult.get().getId();
	}

	/**
	 * checkReports.html表示前に入力情報を成形するメソッド
	 * @param tempReports：入力情報保持インスタンス
	 * @return tempReprots：成形後
	 * */
	public TempReports moldTempReports(TempReports tempReports) {
		//契約名称・製品名・担当者を成形
		Optional<M_Contract> tempContract = mContractRepository.findById(tempReports.getTempRepairingForm().getFkContractId());
		tempReports.getTempRepairingForm().setContractName(tempContract.get().getContractName());
		Optional<M_Product> tempProduct = mProductRepository.findById(tempReports.getTempRepairingForm().getFkProductId());
		tempReports.getTempRepairingForm().setProductName(tempProduct.get().getProductName());
		Optional<M_User> tempUser = mUserRepository.findById(tempReports.getTempRepairingForm().getFkUserId());
		tempReports.getTempRepairingForm().setUserName(tempUser.get().getUserName());
		tempReports.getTempRepairingForm().setUserId(tempUser.get().getUserId());
		
		for (int i = 0; i < tempReports.getTempUsedItemsList().size(); i++) {
				String currentItemNo =tempReports.getTempUsedItemsList().get(i).getItemNo();
				if (currentItemNo.equals("")) {
					break; 
				}
				Optional<M_Inventory>tempInventory = mInventoryRepository.findByItemNo(currentItemNo);
				tempReports.getTempUsedItemsList().get(i).setItemName(tempInventory.get().getItemName());
		}
				
		return tempReports;
	}
	/**
	 * dbに入力した修理内容を登録するメソッド
	 * @param tempReports：入力情報保持フォーム
	 * */
	public void resistReports(TempReports tempReports,@AuthenticationPrincipal User user) {
		resistRepairReport(tempReports.getTempRepairingForm(),user);
		Integer repairId =0; 
		//条件分岐：使用部品の有無
		if( !tempReports.getTempUsedItemsList().get(0).getItemNo().equals("")) {
			//登録したレコードを逆取得し、該当レコードidを取得
			Optional<Repairing>result =  repairingRepository.findByRepairNo(tempReports.getTempRepairingForm().getRepairNo());
			 repairId= result.get().getId();
			 resistUsedItemsReport(tempReports.getTempUsedItemsList(),repairId,user);
		}
	}

	/**
	 * 修理報告書の内容を登録するメソッド
	 * @param tempRepairingForm：修理報告書(tempRepairingForm)の情報
	 * */
	public void resistRepairReport(TempRepairingForm tempRepairingForm,@AuthenticationPrincipal User user) {
		Repairing repairingData = new Repairing();
		repairingData.setId(null);
		repairingData.setFkContractId(tempRepairingForm.getFkContractId());
		repairingData.setRepairNo(tempRepairingForm.getRepairNo());
		repairingData.setFkProductId(tempRepairingForm.getFkProductId());
		repairingData.setMachineNo(tempRepairingForm.getMachineNo());
		repairingData.setRequestDate(tempRepairingForm.getRequestDate());
		repairingData.setCompletionDate(tempRepairingForm.getCompletionDate());
		repairingData.setDeadlineDate(tempRepairingForm.getDeadlineDate());
		repairingData.setFkUserId(tempRepairingForm.getUserId());
		repairingData.setRequestDetails(tempRepairingForm.getRequestDetails());
		repairingData.setRequestCondition(tempRepairingForm.getRequestCondition());
		repairingData.setReproducibility(tempRepairingForm.getReproducibility());
		repairingData.setRepairDetails(tempRepairingForm.getRepairDetails());
		repairingData.setClassification(tempRepairingForm.getClassification());
		repairingData.setRemarks(tempRepairingForm.getRemarks());
		Optional<M_User> findResult = mUserRepository.findByUserId(user.getUsername());
		String author = findResult.get().getUserName();
		repairingData.setAuthor(author);
		repairingData.setCreationTimeStamp(LocalDateTime.now());
				
		//repairingRepository.save(repairingData);	
	}
	
	/**
	 * 修理報告書の内容を登録するメソッド
	 * @param tempUsedItemsList：使用部品報告書(List<TempUsedItemsReportForm>)の情報
	 * @param ログインuser情報
	 * */
	private void resistUsedItemsReport(List<TempUsedItemsReportForm> tempUsedItemsList,Integer repairId,@AuthenticationPrincipal User user) {
		//拡張for構文でリストを一件ずつ登録していく
		for(TempUsedItemsReportForm usedItem : tempUsedItemsList) {
			if(usedItem.getItemNo().equals("")) {
				break;
			}
			
			Optional<M_User> findResult = mUserRepository.findByUserId(user.getUsername());
			String currentUser = findResult.get().getUserName();
			LocalDateTime now = LocalDateTime.now();
			//fkInvenotryId用
			Optional<M_Inventory>itemRecord =mInventoryRepository.findByItemNo(usedItem.getItemNo());

			InventoryLog currentInventoryLog = new InventoryLog();
			currentInventoryLog.setId(null);
			currentInventoryLog.setFkInventoryId(itemRecord.get().getId());
			currentInventoryLog.setFkProcessTypeId(1);//procecssTypeテーブルよりid=1：出庫
			currentInventoryLog.setChangedQuantity(-usedItem.getQuantity());
			currentInventoryLog.setStockAtTheTime(itemRecord.get().getStock()-usedItem.getQuantity());			
			currentInventoryLog.setAuthor(currentUser);
			currentInventoryLog.setCreationTimeStamp(now);
			
			inventoryLogRepository.save(currentInventoryLog);
			//登録したinventoryLogをcreationTimeStampから参照（idを取得するため）
			Optional<InventoryLog>inventoryLogRecord = inventoryLogRepository.findByCreationTimeStamp(now);
			
			UsedItemsReport currentUsedItemsReport =new UsedItemsReport();
			currentUsedItemsReport.setId(null);
			currentUsedItemsReport.setFkInventorylogId(inventoryLogRecord.get().getId());
			currentUsedItemsReport.setFkRepairIngId(repairId);
			currentUsedItemsReport.setQuantity(usedItem.getQuantity());
			currentUsedItemsReport.setAuthor(currentUser);
			currentUsedItemsReport.setCreationTimeStamp(now);
			
			usedItemsReportRepository.save(currentUsedItemsReport);
			
			//在庫データの更新
			M_Inventory inventory = itemRecord.get();
			inventory.setStock(currentInventoryLog.getStockAtTheTime());
			inventory.setChanger(currentUser);
			inventory.setUpadateTimeStamp(now);
			mInventoryRepository.save(inventory);
		}
	}
	
	
	
}

package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.M_Contract;
import com.example.entity.M_Inventory;
import com.example.entity.M_Product;
import com.example.entity.M_User;
import com.example.entity.TargetProducts;
import com.example.form.TempReports;
import com.example.mapper.TargetProductsMapper;
import com.example.repository.M_ContractRepository;
import com.example.repository.M_InventoryRepository;
import com.example.repository.M_ProductRepository;
import com.example.repository.M_UserRepository;
import com.example.repository.RepairingRepository;
import com.example.repository.TargetProductsRepository;

import lombok.RequiredArgsConstructor;

@Service
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
		System.out.println("moldTempReports is called");
		//契約名称・製品名・担当者を成形
		Optional<M_Contract> tempContract = mContractRepository.findById(tempReports.getTempRepairingForm().getFkContractId());
		tempReports.getTempRepairingForm().setContractName(tempContract.get().getContractName());
		Optional<M_Product> tempProduct = mProductRepository.findById(tempReports.getTempRepairingForm().getFkProductId());
		tempReports.getTempRepairingForm().setProductName(tempProduct.get().getProductName());
		Optional<M_User> tempUser = mUserRepository.findById(tempReports.getTempRepairingForm().getFkUserId());
		tempReports.getTempRepairingForm().setUserName(tempUser.get().getUserName());
		
		for (int i = 0; i < tempReports.getTempUsedItemsList().size(); i++) {
				String currentItemNo =tempReports.getTempUsedItemsList().get(i).getItemNo();
				if (currentItemNo.equals("")) {
					break; 
				}
				Optional<M_Inventory>tempInventory = mInventoryRepository.findByItemNo(currentItemNo);
				tempReports.getTempUsedItemsList().get(i).setItemName(tempInventory.get().getItemName());
		}
		
		System.out.println("moldTempReports is succeess");
		
		return tempReports;
	}
}

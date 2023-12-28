package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.M_Contract;
import com.example.entity.M_Product;
import com.example.entity.M_User;
import com.example.entity.TargetProducts;
import com.example.mapper.TargetProductsMapper;
import com.example.repository.M_ContractRepository;
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
	
	/**m_productテーブルDAO*/
	private final TargetProductsRepository targetProductsRepository;
	
	@Autowired
    private TargetProductsMapper contractProductMapper;
	
	/**inventoryテーブルDAO*/
	//private final InventoryRepository inventoryRepository;
	
	/**
	 * m_userテーブルから全情報を取得*/
	public List<M_User> getUserList() {
		return mUserRepository.findAll();
	}
	
	/**m_contractテーブルから全件取得*/
	public List<M_Contract>getContractList(){
		return mContractRepository.findAll();
	}
	
	/**m_productテーブルから全件取得*/
	public List<M_Product>getProductList(){
		return mProductRepository.findAll();
	}
	
	/**target_contractテーブルから全権取得*/
	public List<TargetProducts>getTargetProductsList(){
		return targetProductsRepository.findAll();
	}
	/**
	 * target_contractテーブルで
	 * 引数（fk_contract_id）と一致するListを生成
	 * @param m_contract.id=target_products.fk_contract_id
	 * @return List<TargetProducts> 引数(fk_contract_id)でフィルタしたリスト。
	 * */
	public List<M_Product> getListFilteredByContractId(Integer selectedContractId){
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
	
}


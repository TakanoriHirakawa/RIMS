package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.TargetProducts;

public interface TargetProductsRepository extends JpaRepository<TargetProducts,Integer>{

}
///**idによる検索*/
//Optional<TargetProducts>findById(M_Product id);
///**fk_contract_Idによる検索*/
//Optional<M_Contract>findByFkContractId(Integer fkContractId);
///**fk_product_Idによる検索*/
//Optional<M_Product>findByFkProductId(Integer fkProductId);
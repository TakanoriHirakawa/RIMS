package com.example.Mapper;
/*java/com/example/Mapper/TargetProductsMapper.java*/
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.entity.M_Product;
@Mapper
public interface TargetProductsMapper {

	List<M_Product>getListFilteredBySelectedContractId(@Param("selectedContractId") Integer selectedContractId);
	
}
//@Select({
////"SELECT * FROM  target_products tp",
////"INNER JOIN m_contract  ON fk_contract_id = m_contract.id",
////"WHERE m_contract.id = #{selectedContractId}",
//
//"""
//SELECT tp.id as id,tp.fk_contract_id as fk_contract_id,tp.fk_product_id as fk_product_id
//FROM  target_products tp
//INNER JOIN m_contract mc ON tp.fk_contract_id = mc.id 
//WHERE mc.id=1
//"""
//
//})
//List<TargetProducts>getListFilteredByContractId(@Param("selectedContractId") Integer contractId);

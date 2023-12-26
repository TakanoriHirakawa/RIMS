package com.example.mapper;
/*java/com/example/Mapper/TargetProductsMapper.java*/
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.entity.M_Product;
@Mapper
public interface TargetProductsMapper {

	List<M_Product>getListFilteredBySelectedContractId(@Param("selectedContractId") Integer selectedContractId);
	
}

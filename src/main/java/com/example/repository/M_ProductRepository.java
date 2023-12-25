package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.M_Product;

public interface M_ProductRepository extends JpaRepository<M_Product,Integer> {
	/**idによる1件検索*/
	Optional<M_Product>findById(Integer id);
	/**製品名称による１件検索*/
	Optional<M_Product>findByProductName(String productName);
	/**製品IDによる１件検索*/
	Optional<M_Product>findByproductId(String productId);
	/**List<Integer>を引数として、List内の番号と一致するidのList<M_product>を生成*/
	List<M_Product>findByIdIn(List<Integer>IdList);

}

package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * m_productのEntityクラス
 * @param id：一意性id(主キー)(自動生成)
 * @param category：カテゴリ名称（フィルタ用）
 * @param productName：製品名称
 * @Param productId：製品ID()
 * */
@Entity
@Table(name=" m_product")
@Data
public class M_Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String category;
	@Column(name="product_name")
	private String productName;
	@Column(name="product_id")
	private Integer productId;

}

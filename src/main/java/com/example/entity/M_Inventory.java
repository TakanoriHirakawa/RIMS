package com.example.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * m_inventoryのEntityクラス
 * @param id：一意性id(主キー)(自動生成)
 * @param fkProductId：m_productの主キー（外部キー）
 * @param itemNo：図番
 * @param itemName：物品名称
 * @param stock：在庫数量
 * @param unitPrice：単価
 * @param stockMax：発注点上限
 * @param stockMin：発注点下限
 * @param statusDone：発注ステータス_発注済（trueで発注済）（デフォルトはfalse）
 * @param statusUnable：発注ステータス_発注不可（trueで発注不可）（デフォルトはfalse）
 * @param statusMessage：発注ステータス_コメント（発注日等の情報）
 * @param remarks：備考
 * */
@Entity
@Table(name="m_inventory")
@Data
public class M_Inventory {
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="fk_product_id")
	private Integer fkProductId;
	@Column(name="item_no")
	private String itemNo;
	@Column(name="item_name")
	private String itemName;
	private Integer stock;
	@Column(name="unit_price")
	private Integer unitPrice;
	@Column(name="basic_quantity")
	private Integer basicQuantity;
	@Column(name="stock_min")
	private Integer stockMin;
	@Column(name="stock_max")
	private Integer stockMax;
	@Column(name="status_done")
	private Boolean statusDone=false;
	@Column(name="status_unable")
	private Boolean statusUnable=false;
	@Column(name="status_message")
	private String statusMessage;
	private String remarks;
	private String author;
	@Column(name = "creation_timestamp")
	private LocalDateTime creationTimeStamp;
	private String changer;
	@Column(name = "update_timestamp")
	private LocalDateTime upadateTimeStamp;
	

}

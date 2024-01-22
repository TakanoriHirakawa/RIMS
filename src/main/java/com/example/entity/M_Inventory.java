package com.example.entity;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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
	@Column(name="status_done")
	private boolean statusDone=false;
	@Column(name="status_unable")
	private boolean statusUnable=false;
	@Column(name="status_message")
	private String statusMessage;
	private String remarks;

}

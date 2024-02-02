package com.example.form;

import lombok.Data;
/**
 * m_inventoryのformクラス
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
@Data
public class InventoryForm {
	private Integer id;
	private Integer fkProductId;
	private String itemNo;
	private String itemName;
	private Integer stock;
	private Integer unitPrice;
	private Integer basicQuantity;
	private Integer stockMin;
	private Integer stockMax;
	private Boolean statusDone=false;
	private Boolean statusUnable=false;
	private String statusMessage;
	private String remarks;
	
	
}

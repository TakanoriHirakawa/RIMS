package com.example.form;

import lombok.Data;
/**
 *入庫処理の情報を一時的に保持するクラス
 * @param productName：主要製品名
 * @param itemNo：図番
 * @param itemName：品名
 * @param quantity：数量
 * */
@Data
public class RecevingItem {
	private Integer productName;
	private String itemNo;
	private String itemName;
	private Integer quantity;	
}

package com.example.form;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
/**
 * inventory/receiving_items.htmlの入力情報保持用フォーム
 * @param recevingItemList：入力情報保持クラスRecevingItemのListオブジェクト
 *  @implNote：引数無コンストラクタでrecevingItemList[50]を生成
 * */
@Data
public class TempRecevingItemList {
	private final List<RecevingItem> recevingItemList;
	
	public  TempRecevingItemList () {
		this.recevingItemList = new ArrayList<>();
		for(int i =0 ;i<50;i++) {
			this.recevingItemList.add(new RecevingItem());
		}
	}
}

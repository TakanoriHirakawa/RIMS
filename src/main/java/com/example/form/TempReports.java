package com.example.form;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 修理報告の入力情報を保持するFormObject
 * @param tempRepairingForm：修理報告書の入力情報
 * @param tempUsedItemsList：使用部品報告書の入力情報
 * @implNote：コンストラクタは、各paramを生成する。使用部品報告書は50件のListを生成。
 * */

@Data
public class TempReports {
	private final TempRepairingForm tempRepairingForm;
	private final List<TempUsedItemsReportForm> tempUsedItemsList;
	
	public TempReports() {
			this.tempRepairingForm = new TempRepairingForm();
			this.tempUsedItemsList = new ArrayList<>();
			for(int i=0;i<50;i++) {
				this.tempUsedItemsList.add(new TempUsedItemsReportForm());
			}
	}
}

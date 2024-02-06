package com.example.form;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 作成中の使用部品報告書の一時的なフォーム
 * db上にテーブル無
 * @param id：一意性id（主キー）不要？
 * @param fkRepairingId：repairingテーブル主キー（外部キー）不要？
 * @param fkInventoryLogId：inventory_logテーブル主キー（外部キー）不要?
 * @param fkProcessTypeId：process_typeテーブル主キー（外部キー）_InventoryLog.fkProcessTypeId
 * @param itemNo：図番
 * @param quantity：数量_UsedItemsReport.quantity & InventoryLog.changedQuantity
 * @param stockAtTheTime：変更適用後時点の在庫数_InventoryLog.stockAtTheTime
 * @param author：ログ作成者_InventoryLog.author
 * @param creationTimeStamp：ログ作成時タイムスタンプ_InventoryLog.creationTimeStamp
 * */

@Data
public class TempUsedItemsReportForm{
	private Integer index;
	private Integer fkRepairingId;
	private Integer fkInventoryLogId;
	private Integer fkProcessTypeId;
	private String itemNo;  
	private Integer quantity;
	private String author;
	private LocalDateTime creationTimeStamp;

}

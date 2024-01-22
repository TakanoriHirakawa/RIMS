package com.example.form;

import lombok.Data;

/**
*used_items_reportフォーム
* @param id：一意性id（主キー）
* @param fkRepairingId：repairingテーブル主キー（外部キー）
* @param fkInventoryLogId：inventory_logテーブル主キー（外部キー）
* @param quantity：数量
* */

@Data
public class UsedItemsReportForm {
	private final Integer id;
	private final Integer fkRepairIngId;
	private final Integer fkInventorylogId;
	private final Integer quontity;
}

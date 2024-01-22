package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * used_items_reportのEntityクラス
 * @param id：一意性id（主キー）
 * @param fkRepairIngId：repairingテーブル主キー（外部キー）
 * @param fkRepairIngId：inventory_logテーブル主キー（外部キー）
 * @param quantity：数量
 * */

@Entity
@Data
@Table(name="used_items_report")
public class UsedItemsReport {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private final Integer id;
	@Column(name="fk_repairing_id")
	private final Integer fkRepairIngId;
	@Column(name="fk_inventory_log_id")
	private final Integer fkInventorylogId;
	private final Integer quantity;
}

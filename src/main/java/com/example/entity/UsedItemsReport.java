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
	private Integer id;
	@Column(name="fk_repairing_id")
	private Integer fkRepairIngId;
	@Column(name="fk_inventory_log_id")
	private Integer fkInventorylogId;
	private Integer quantity;
	private String author;
	@Column(name = "creation_timestamp")
	private LocalDateTime creationTimeStamp;
}

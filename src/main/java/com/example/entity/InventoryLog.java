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
 * inventory_logテーブルのEntityクラス
 * @param id：一意性id（主キー）
 * @param fkInventoryId：m_inventoryテーブル主キー（外部キー）
 * @param fkProcessTypeId：process_typeテーブル主キー（外部キー）
 * @param changedQuantity：変更数量
 * @param stockAtTheTime：変更適用後時点の在庫数
 * @param author：ログ作成者
 * @param LocalDateTime：ログ作成時タイムスタンプ
 * */

@Entity
@Data
@Table(name = "inventory_log")
public class InventoryLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "fk_m_inventory_id")
	private Integer fkInventoryId;
	@Column(name = "fk_process_type_id")
	private Integer fkProcessTypeId;
	@Column(name = "changed_quantity")
	private Integer changedQuantity;
	@Column(name = "stock_at_the_time")
	private Integer stockAtTheTime;
	private String author;
	@Column(name = "creation_timestamp")
	private LocalDateTime creationTimeStamp;

}

package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * process_typeのEntityクラス
 * @param id：一意性id（主キー）
 * @param processName：処理の名称
 * */

@Entity
@Data
@Table(name = "process_type")
public class Process_Type {
	@Id
	private final Integer id;
	@Column(name = "process_name")
	private final String processName;
}

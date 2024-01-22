package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Integer id;
	@Column(name = "process_Name")
	private final String processName;
}

package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * m_contractのEntityクラス
 * @param id：一意性id(主キー)(自動生成)
 * @param contractId:契約id（）
 * @param contractName：契約名称
 * @Param basicFee：基本金額
 * @param completionFee：完了金額
 * @param estimateFee：見積金額
 * @param remarks：備考
 * 
 * */
@Entity
@Table(name=" m_contract")
@Data
public class M_Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="contract_id")
	private String contractId;
	@Column(name="contract_name")
	private String contractName;
	@Column(name="basic_fee")
	private Integer basicFee;
	@Column(name="completion_fee")
	private Integer completionFee;
	@Column(name="estimate_fee")
	private Integer estimateFee;
	private String remarks;
	
}

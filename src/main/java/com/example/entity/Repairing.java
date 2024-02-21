package com.example.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *  repairingのEntityクラス
 * @param id：一意性id(主キー)
 * @param fkContractId：m_Contractテーブル主キー(外部キー)
 * @param repairNo：修理管理番号
 * @param fkProductId：m_productテーブル主キー（外部キー）
 * @param machineNo：機番
 * @param requestDate：依頼日
 * @param completionDate：完了日（デフォルト：当日）
 * @param  deadlineDate：納期日（依頼日+契約から算出した値）
 * @param  fkUserId：m_userテーブル主キー（外部キー）
 * @param requestDetails：客先障害内容
 * @param requestCondition：実機の状態（受入時の状態）
 * @param reproducibility：再現性（再現・未再現・その他から選択）
 * @param repairDetails：修理実施内容の詳細
 * @param classification：作業区分（完了・見積・未修理から選択）
 * @param remarks：備考
 * 
 */

@Entity
@Table(name="repairing")
@Data
public class Repairing {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  Integer id;
	@Column(name="fk_contract_id")
	private  Integer fkContractId;
	@Length(max = 20)
	@Column(name="repair_no")
	private  String repairNo;
	@Column(name="fk_product_id")
	private  Integer fkProductId;
	@Column(name="machine_no")
	private  String machineNo;
	private  LocalDate requestDate;
	private  LocalDate completionDate;
	private  LocalDate deadlineDate;
	@Column(name="fk_m_user_id")
	private  String fkUserId;
	@Column(name="request_details")
	private  String requestDetails;
	@Column(name="request_condition")
	private  String requestCondition;
	private  String reproducibility;
	@Column(name="repair_details")
	private  String repairDetails;
	private  String classification;
	private  String remarks;
	private  String author;
	@Column(name="creation_timestamp")
	private  LocalDateTime creationTimeStamp;

	

}

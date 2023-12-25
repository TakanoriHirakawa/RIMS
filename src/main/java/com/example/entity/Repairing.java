package com.example.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

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
 * @Param fkProductId：m_productテーブル主キー（外部キー）
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
	private final Integer id;
//private final Integer fkContractId
	@Length(max = 20)
	private final String repairNo;
//private final Integer fkProductId;
	private final String machineNo;
	private final Date requestDate;
	private final Date completionDate;
	private final Date deadlineDate;
//	private final Date fkUserId;
	private final String requestDetails;
	private final String requestCondition;
	private final String reproducibility;
	private final String repairDetails;
	private final String classification;
	private final String remarks;

}

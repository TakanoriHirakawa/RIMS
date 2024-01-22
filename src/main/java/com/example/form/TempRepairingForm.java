package com.example.form;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 作成中の修理報告書の一時的なフォーム
 * db上にテーブル無
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
@Data
public class TempRepairingForm {
	private Integer id;
	private Integer fkContractId;
	@Length(max = 20)
	private String repairNo;
	private  Integer fkProductId;
	private  String machineNo;
	private  LocalDate requestDate;
	private  LocalDate completionDate;
	private  LocalDate deadlineDate;
	private  Integer fkUserId;
	private  String requestDetails;
	private  String requestCondition;
	private  String reproducibility;
	private  String repairDetails;
	private  String classification;
	private  String remarks;
}

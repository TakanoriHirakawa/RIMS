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
 * m_userのEntiryクラス
 * @param id：連番(自動生成)
 * @param userId：ユーザーID（主キー）
 * @param userName：ユーザー名
 * @param displayName：表示名
 * @param password：パスワード
 * @param fkAuthorityId：m_authorityの主キー（権限テーブル主キー）（外部キー）
 * */
@Entity
@Table(name="m_user")
@Data
public class M_User {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	@Id
	@Column(name="user_id")
	private String userId;
	@Column(name="user_name")
	private String userName;
	
	//TODO：桁数指定を設定
	private String password;
	@Column(name="fk_authority_id")
	private Integer fkAuthorityId;
	
	private String author;
	@Column(name = "creation_timestamp")
	private LocalDateTime creationTimeStamp;
	private String changer;
	@Column(name = "update_timestamp")
	private LocalDateTime upadateTimeStamp;
	
}

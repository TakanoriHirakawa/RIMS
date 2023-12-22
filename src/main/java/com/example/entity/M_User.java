package com.example.entity;

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
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id; 
	
	@Id
	@Column(name="user_id")
	private String userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="display_name")
	private String displayName;

	private String password;
	
	@Column(name="fk_authority_id")
	private Integer fkAuthorityId;
}

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
 * idは自動連番生成
 * */
@Entity
@Table(name="m_user")
@Data
public class M_User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="display_name")
	private String displayName;

	private String password;
	
	@Column(name="fk_authority_id")
	private Integer fk_authority_id;
}

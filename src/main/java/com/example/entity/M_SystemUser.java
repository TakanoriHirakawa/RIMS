package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="m_system_user")
@Data
public class M_SystemUser {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 
	
	//TODO Stringに修正
	@Column(name="system_user_id")
	private String systemUserId;
	
	@Column(name="system_user_name")
	private String systemUserName;
	
	private String password;
	
	@Column(name="fk_authority")
	private Integer authority;
}

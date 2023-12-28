package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * m_authorityのEntityクラス
 * @param id：連番(自動生成)(主キー)
 * @param authority：権限名称
 * */

@Entity
@Table(name="m_authority")
@Data
public class M_Authority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String authority;

}

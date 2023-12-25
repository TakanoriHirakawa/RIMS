package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="target_products")
@Data
public class TargetProducts {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
//	@ManyToOne
//	@JoinColumn(name="fk_contract_id" ,referencedColumnName = "id")
//	private M_Contract fkContractId;
//	@ManyToOne
//	@JoinColumn(name="fk_product_id" ,referencedColumnName = "id")
//	private M_Product fkProductId;
@Column(name="fk_contract_id")
	private Integer fkContractId;
@Column(name="fk_product_id")
	private Integer fkProductId;
}

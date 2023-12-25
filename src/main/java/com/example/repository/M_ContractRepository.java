package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.M_Contract;

public interface M_ContractRepository extends JpaRepository<M_Contract, Integer>{
	/**idによる検索*/
	Optional<M_Contract>findById(Integer id);
	/**契約名称による検索*/
	Optional<M_Contract>findByContractName(String contractName);
	}

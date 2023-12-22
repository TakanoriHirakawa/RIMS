package com.example.service;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.example.entity.M_User;
import com.example.repository.M_UserRepository;
import com.example.repository.RepairingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepairingService {
	/**m_userテーブルDAO*/
	private final M_UserRepository mUserRepository;

	/**repairingテーブルDAO*/
	private final RepairingRepository repairingRepository;

	/**inventoryテーブルDAO*/
	//private final InventoryRepository inventoryRepository;

	/**Dozer Mappaer*/
	private final Mapper mapper;
	/**
	 * m_userテーブルから全情報を取得*/
	public List<M_User> getUserList() {
		return mUserRepository.findAll();


	}

}

package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.M_User;
import com.example.repository.M_UserRepository;

import lombok.RequiredArgsConstructor;
/**
 * ログイン画面 Serviceクラス
 * 
 * */
@Service
@RequiredArgsConstructor
public class LoginService {
	/**M_UserテーブルDAO*/
	private final M_UserRepository repository;
	/**PasswordEncoder*/
//	private final PasswordEncoder passwordEncoder;
	
	public Optional<M_User> findUserByUserId(String userId){
		return repository.findByUserId(userId);
	}
	
}

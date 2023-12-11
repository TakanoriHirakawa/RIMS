package com.example.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.example.entity.M_SystemUser;
import com.example.form.SignupForm;
import com.example.repository.SystemUserRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面
 *
 */

@Service
@RequiredArgsConstructor
public class SignupService {
	
	/**ユーザー情報テーブルDAO(DataAccessObject)*/
	private final SystemUserRepository repository;
	
	/**Dozer Mapper*/
	private final Mapper mapper;
	
	//TODO：パスワードのハッシュ化
	//private final PasswordEncoder passwordEncoder;

	/**
	 *システムユーザー情報テーブル
	 * @param form 入力情報
	 * @return　登録情報(entity/M_SystemUser)
	 */
	
	public M_SystemUser resistSystemUser(SignupForm form){
		M_SystemUser userInfo =mapper.map(form, M_SystemUser.class);
		
		return repository.save(userInfo);
		
	}
}

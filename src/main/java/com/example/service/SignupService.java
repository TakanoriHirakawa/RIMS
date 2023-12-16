package com.example.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.example.entity.M_User;
import com.example.form.SignupForm;
import com.example.repository.M_UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー登録画面
 *
 */

@Service
@RequiredArgsConstructor
public class SignupService {

	/**ユーザー情報テーブルDAO(DataAccessObject)*/
	private final M_UserRepository repository;

	/**Dozer Mapper*/
	private final Mapper mapper;

	//TODO：パスワードのハッシュ化
	//private final PasswordEncoder passwordEncoder;

	/**
	 *システムユーザー情報テーブル
	 * @param form 入力情報
	 * @return　登録情報(entity/m_user)
	 */

	public Optional<M_User> resistUser(SignupForm form) {
		
		var userInfoExisted = repository.findByUserId(form.getUserId());
		//既に登録済みのuserがいる場合
		if (userInfoExisted.isPresent()) {
			return Optional.empty();
		}
		M_User userInfo = mapper.map(form, M_User.class);
		
		return Optional.of(repository.save(userInfo));

	}
}

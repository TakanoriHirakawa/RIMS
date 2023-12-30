package com.example.authentication;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.repository.M_AuthorityRepository;
import com.example.repository.M_UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {
	/**M_UserテーブルRepository*/
	private final M_UserRepository userRepository;
	/**M_AuthorityrテーブルRepository*/
	private final M_AuthorityRepository authRepository;
	
	/**
	 * ユーザー情報生成
	 * @param username -> M_User.userId
	 * @throws UsernameNotFoundException
	 * 
	 * 引数と一致するuserIdを持つレコードの
	 * userId,password,fkAuthorityIdを取得。
	 * fkAuthorityIdを引数にして、
	 * m_authorityテーブルからauthorityを取得し
	 * rolesに格納した後、build。
	 * */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var userInfo = userRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));
		
		var result = User.withUsername(userInfo.getUserId())
				.password(userInfo.getPassword())
				.authorities(authRepository.findById(userInfo.getFkAuthorityId()).get().getAuthority())
				.build();
		
		return result;
	}

}

package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.constant.MessageCheckBind;
import com.example.constant.SignupMessage;
import com.example.entity.M_User;
import com.example.form.SignupForm;
import com.example.repository.M_UserRepository;
import com.example.util.AppUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
	/**m_userテーブルDAO*/
	private final M_UserRepository mUserRepository;
	
	/**パスワードのハッシュ化*/
	private final PasswordEncoder passwordEncoder;
	
	private final MessageSource  messageSource;

	/**
	 * ユーザー登録フォームの生成
	 * @return signupForm：登録情報保持フォーム
	 * */
	public SignupForm createNewSignupForm() {
		return new SignupForm();
	}
	/**
	 * ユーザー登録処理
	 * @param signupForm：登録情報
	 * @param user：ログイン認証ユーザー
	 * */
	public void resistUser(SignupForm signupForm,@AuthenticationPrincipal User user) {
		List<M_User> findResult = mUserRepository.findAll();
		
		M_User userInfo = new M_User();
		userInfo.setId(findResult.size()+1);
		userInfo.setUserId(signupForm.getUserId());
		userInfo.setUserName(signupForm.getUserName());
		userInfo.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		userInfo.setFkAuthorityId(signupForm.getFkAuthorityId());
		userInfo.setAuthor(user.getUsername());
		userInfo.setCreationTimeStamp(LocalDateTime.now());
		mUserRepository.save(userInfo);
		
		log.info("resistUser is success");
	}
	
	/**
	 * userIdの重複チェック
	 * @param : userId
	 * @return : MessageCheckBind Class
	 * */
	public MessageCheckBind checkUserIdDuplicate(String userId) {
		Optional<M_User>findResult = mUserRepository.findByUserId(userId);
		SignupMessage messageBind;
		if (findResult.isEmpty()) {
			messageBind= SignupMessage.EXISTED_ID;
		}else {
			messageBind= SignupMessage.USABLE_ID;
		}
		
		MessageCheckBind result = new MessageCheckBind();
		result.setMessage(AppUtil.getMessage(messageSource, messageBind.getMessageId()));
		result.setFlg(messageBind.isError()); //flgの判定はsignupMessageのisError
		
		return result;
		
		
	}
}

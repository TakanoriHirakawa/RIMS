package com.example.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.constant.MessageConst;
import com.example.entity.M_User;
import com.example.form.SignupForm;
import com.example.service.SignupService;
import com.example.util.AppUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignupController {

	private final SignupService service;

	private final MessageSource messageSource;

	@GetMapping("/signup")
	public String getSignup(Model model, SignupForm form) {
		return "signup/signup";
	}

	@PostMapping("/signup")
	public String postSignup(Model model, SignupForm form) {
		//入力情報を取得
		Optional<M_User> userInfoOpt = service.resistUser(form);

		var message = AppUtil.getMessage(messageSource, this.judgeMessageKey(userInfoOpt));
		//エラーメッセージの格納
		model.addAttribute("message", message);
		return "signup/signup";
	}

	/**
	 * @param userInfoIot
	 * */
	private String judgeMessageKey(Optional<M_User> userInfoOpt) {
		if (userInfoOpt.isEmpty()) {
			return MessageConst.SIGN_UP_EXISTED_ID;
		}
		return MessageConst.SIGN_UP_SUCCESS;
	}

}

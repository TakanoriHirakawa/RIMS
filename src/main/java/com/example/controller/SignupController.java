package com.example.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.M_User;
import com.example.form.SignupForm;
import com.example.service.SignupService;

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
		M_User userInfo = service.resistUser(form);

		System.out.println(userInfo.toString());

		//成功時のメッセージを設定。
//		String successMsg = AppUtil.getMessage(messageSource, MessageConst.SIGN_UP_SUCCESS);
//
//		model.addAttribute("signup.success", successMsg);

		return "signup/signup";
	}
}

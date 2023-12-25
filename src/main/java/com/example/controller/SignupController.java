package com.example.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.constant.SignupMessage;
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
	public String postSignup(Model model, @Validated SignupForm form, BindingResult bdResult) {
		if(bdResult.hasErrors()) {
			return "signup/signup" ;
		}
		//入力情報を取得
		Optional<M_User> userInfoOpt = service.resistUser(form);
		SignupMessage messageBind = judgeMessageKey(userInfoOpt);
		var message = AppUtil.getMessage(messageSource, messageBind.getMessageId());
		//エラーメッセージの格納
		model.addAttribute("message", message);
		model.addAttribute("isError", messageBind.isError());
		
		return "redirect:/login";
	}

	/**
	 * ユーザー情報登録の結果メッセージキーを判断する
	 * @param userInfoOpt ユーザー登録結果（登録済みIDの場合は、Empty）
	 * @retunn SignupMessage messageKeyと登録成否のバインド（falseで成功）
	 * */

	private SignupMessage judgeMessageKey(Optional<M_User> userInfoOpt) {
		if (userInfoOpt.isEmpty()) {
			return SignupMessage.EXISTED_ID;
		}
		return SignupMessage.SUCCEED;
	}

}

package com.example.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.constant.MessageConst;
import com.example.service.LoginService;
import com.example.util.AppUtil;

import lombok.RequiredArgsConstructor;

/**
 * ログイン画面 Controller
 * 
 * */
@Controller
@RequiredArgsConstructor
public class LoginController {

	/**ログイン画面 Serviceクラス*/
	private final LoginService service;

	/**メッセージソース*/
	private final MessageSource messageSource;

	/**
	 * 初期表示
	 * @param model 
	 * @param error：ログイン認証時にエラーが発生した場合のパラメータ
	 * @return 初期表示画面（/login/login.html）
	 * */
	@GetMapping("/login")
	public String getLogin(Model model,@RequestParam(name = "error", required = false) String error) {
		model.addAttribute("loginForm", service.createLoginForm());
		if (error != null) {
            String errorMsg = AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_INPUT, Locale.getDefault());
            model.addAttribute("errorMsg", errorMsg);
        } else {
            model.addAttribute("errorMsg", "");
        }
		return "/login";
	}
	
	/**
	 * ログアウトの処理
	 * */
	@PostMapping("/logout")
	public String getLogout() {
		return "redirect:/login";
	}


}

package com.example.controller;

import java.util.Locale;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.constant.MessageConst;
import com.example.entity.M_User;
import com.example.form.LoginForm;
import com.example.service.LoginService;
import com.example.util.AppUtil;

import jakarta.servlet.http.HttpSession;
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

	/**PasswordEncoder*/
	private final PasswordEncoder passwordEncoder;

	/**メッセージソース*/
	private final MessageSource messageSource;

	/**セッション情報*/
	private final HttpSession session;

	/**
	 * 初期表示
	 * @param model 
	 * @param form 入力情報
	 * @return 初期表示画面（/login/login.html）
	 * */
	@GetMapping("/login")
	public String getLogin(Model model, LoginForm form) {
		return "/login";
	}

	/**
	 *ログインエラー画面表示
	 * @param model 
	 * @param form 入力情報
	 * @return 初期表示画面（/login/login.html）
	 * */
	@GetMapping(value = "/login", params = "error")
	public String getLoginWithError(Model model, LoginForm form) {
		var errorInfo = (Exception) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		System.out.println(errorInfo.toString());
		return "/login";
	}

	/**
	 * ログインPost処理
	 * @param mdoel
	 * @param form 入力情報
	 * @return ホーム画面(/repair_report/home.html)
	 * */
	@PostMapping("/login")
	public String postLogin(Model model, LoginForm form) {
		model.addAttribute("loginForm", form);

		//ログインフォームからユーザーIDを取得
		String userId = form.getUserId();

		//ユーザーIDを使ってシステムユーザ情報を検索
		Optional<M_User> userInfo = service.findUserByUserId(userId);

		//システムユーザーが存在する&入力パスワードと該当システムユーザーのパスワードが一致するか
		boolean isCorectUserInfo = userInfo.isPresent()
				&& passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());

		if (isCorectUserInfo) {
			System.out.println("ログインに成功" + form.toString());
			//ホーム画面へ
			return "redirect:/repair_repair/home";

		} else {
			//一致しない場合はログイン失敗
			//エラーログインエラー時のメッセージを設定。
			String errorMsg = AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_INPUT, Locale.getDefault());
			System.out.println("ログインに失敗" + form.toString());
			model.addAttribute("errorMsg", errorMsg);
			return "login/login";
		}
	}
}

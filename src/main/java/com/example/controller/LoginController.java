package com.example.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.constant.MessageConst;
import com.example.entity.M_User;
import com.example.form.LoginForm;
import com.example.service.LoginService;
import com.example.util.AppUtil;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService service;

	//private final PasswordEncoder passwordEncoder;

	private final MessageSource messageSource;

	@GetMapping("/login")
	public String getLogin(Model model, LoginForm form) {
		model.addAttribute("loginForm", form);
		return "login/login";
	}

	@PostMapping("/login")
	public String postLogin(Model model, LoginForm form) {
		model.addAttribute("loginForm", form);
		
		//ログインフォームからユーザーIDを取得
		String userId = form.getUserId();

		//ユーザーIDを使ってシステムユーザ情報を検索
		Optional<M_User> userInfo = service.searchUserByUserId(userId);

		//システムユーザーが存在する&入力パスワードと該当システムユーザーのパスワードが一致するか
		//TODO パスワードのハッシュ化
		boolean isCorectUserInfo = userInfo.isPresent()
				&& form.getPassword().equals(userInfo.get().getPassword());

		if (isCorectUserInfo) {
			//一致した場合、登録情報からシステムユーザ名を取得する（後で変更）
			form.setUserName(userInfo.get().getUserName());

			System.out.println("ログインに成功" + form.toString());
			//ホーム画面へ
			return "redirect:repair_report/home";

		} else {
			//一致しない場合はログイン失敗
			//エラーログインエラー時のメッセージを設定。
			String errorMsg = AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_INPUT);

			System.out.println("ログインに失敗" + form.toString());
			model.addAttribute("errorMsg", errorMsg);
			return "login/login";
		}
	}
}

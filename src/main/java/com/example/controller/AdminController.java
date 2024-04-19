package com.example.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.constant.MessageCheckBind;
import com.example.form.SignupForm;
import com.example.service.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	private final AdminService adminService;

	/**
	 * TODO：後々、削除してAcordionButtonでコントロール
	 * adminのGETレスポンス
	 * 	@param model
	 * @param user：ログイン認証ユーザー
	 * @return admin/adminHome.html
	 * */
	@GetMapping("/adminHome")
	public String getAdminHome(Model model, @AuthenticationPrincipal User user) {
		boolean isAdmin = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority().equals("admin"));
		model.addAttribute("isAdmin", isAdmin);

		return "admin/adminHome";
	}

	/**
	 * admin/admin_resister/signupのGETレスポンス
	 * @param model
	 * @param user：ログイン認証ユーザー
	 * @return admin/admin_resister/signup.html
	 * */
	@GetMapping("/admin_resister/signup")
	public String getSignup(Model model, @AuthenticationPrincipal User user) {
		boolean isAdmin = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority().equals("admin"));
		model.addAttribute("isAdmin", isAdmin);

		/*入力情報保持フォームを格納*/
		model.addAttribute("signupForm", adminService.createNewSignupForm());
		return "admin/admin_resister/signup";
	}
	
	@PostMapping("/admin_resister/signup/checkUserIdDuplicate")
	@ResponseBody
	public MessageCheckBind checkUserIdDuplicate(@RequestParam("userId") String userId) {
		log.info(userId);
		return adminService.checkUserIdDuplicate(userId);
	}
	
	@PostMapping("/admin_resister/signup/resistUser")
	public String postResistUser(Model model, @AuthenticationPrincipal User user,
			@RequestParam(name = "error", required = false) String error,
			@ModelAttribute("singupForm") SignupForm signupForm) {
		
		if (error != null ) {
			return "redirect:admin/admin_resister/signup";
		}else {
			adminService.resistUser(signupForm,user);
			return "admin/adminHome";
		}
	}

}

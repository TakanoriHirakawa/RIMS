package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/master_resister")
public class AdminSignupController {
	
	@GetMapping("/signupUser")
	public String getAdminSignupUser() {
		return "admin/master_resister/signupUser";
	}

}

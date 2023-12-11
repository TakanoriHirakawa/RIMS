package com.example.form;

import lombok.Data;

@Data
public class SignupForm {
	private String userId;
	private String password;
	private String userName;
	private String employeeName;
	private String displayName;
	private Integer authorityId;
}

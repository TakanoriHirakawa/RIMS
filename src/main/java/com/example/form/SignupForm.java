package com.example.form;

import lombok.Data;

@Data
public class SignupForm {
	private Integer id;
	private String userId;
	private String password;
	private String userName;
	private String displayName;
	private Integer fkAuthorityId;
}

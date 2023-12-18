package com.example.form;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignupForm {
	private Integer id;
	@Length(min=7,max=30)
	private String userId;
	//TODO:パスワードの桁数範囲を変更/validationMessagesも要編集
	@Length(min=4,max=4,message="digitLenge")
	private String password;
	@Length(max=30)
	private String userName;
	@Length(max=6)
	private String displayName;
	private Integer fkAuthorityId;
}

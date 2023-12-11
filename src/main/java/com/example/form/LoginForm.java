package com.example.form;

import lombok.Data;

/**
 * ログインフォーム
 * m_userテーブルと連結
 * */


@Data
public class LoginForm {
	private String userId;
	private String userName;
	private String displayName;
	private String password;
	private Integer fk_authorityId;
}

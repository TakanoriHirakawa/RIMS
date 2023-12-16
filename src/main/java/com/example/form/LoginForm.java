package com.example.form;

import lombok.Data;

/**
 * ログインフォーム
 * m_userテーブルと連結
 * */


@Data
public class LoginForm {
	private final String userId;
	private final String userName;
	private final String displayName;
	private final String password;
	private final Integer fkauthorityId;
}

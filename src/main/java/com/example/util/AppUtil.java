package com.example.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
/**
 * アプリケーション共通クラス
 * */
public class AppUtil {
	
	public static String getMessage(MessageSource messageSource,String key,Object...params) {
		return messageSource.getMessage(key, params, Locale.JAPAN);
	}
	
}

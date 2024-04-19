package com.example.constant;

import lombok.Data;

/**
 * validationMessageをString型とboolean型の組み合わせで保持するクラス 
 * <br>
 * @param：message/String validationメッセージ
 * @param：flg/Boolean 判定(hasErrorやisEmptyの結果を想定）
 * */

@Data
public class MessageCheckBind {
	private String message;
	private boolean flg;
}

package com.example.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SignupMessage {
		SUCCEED(MessageConst.SIGN_UP_SUCCESS,false),
				
		EXISTED_ID(MessageConst.SIGN_UP_EXISTED_ID,true),
	
		USABLE_ID(MessageConst.SIGN_UP_USABLE_ID,false);
		
		private String messageId;
		
		private boolean isError;
}

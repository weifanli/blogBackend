package com.liweifan.common.error.entity;
/**
 * 登录异常类
 * 作者 weifanl
 * 创建时间 2020年1月23日
 */
public class LoginException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public LoginException() {
		super();
	}

	public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(Throwable cause) {
		super(cause);
	}
	
}

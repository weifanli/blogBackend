package com.liweifan.modules.sys.security.token;

import org.apache.shiro.authc.UsernamePasswordToken;
/**
 * 正常登录token
 * @author weifanl
 * @date:  2020年1月10日 下午2:52:25
 */
public class BaseUsernamePasswordToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 1L;
	
	private Boolean isMobile;
	

	public BaseUsernamePasswordToken() {
		super();
	}

	public BaseUsernamePasswordToken(String username,  String password, boolean rememberMe, String host,Boolean isMobile) {
		super(username, password, rememberMe, host);
		this.isMobile=isMobile;
	}


	public Boolean getIsMobile() {
		return isMobile;
	}

	public void setIsMobile(Boolean isMobile) {
		this.isMobile = isMobile;
	}
}

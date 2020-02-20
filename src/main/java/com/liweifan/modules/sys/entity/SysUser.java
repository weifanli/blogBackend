package com.liweifan.modules.sys.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 系统用户表
 * @author weifanl
 * @date:  2019年9月19日 下午3:23:48
 */
@Table(name = "sys_user")
public class SysUser implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private  String   username;			//登录用户名
	private  String   nickname;			//用户昵称
	private  String   password;			//密码
	private  String   name;			//名字
	private  String   card;			//身份证号
	private  String   email;			//邮箱
	private  Integer   sex;			//性别
	private  String   userType;			//用户类型
	private  String   mobile;			//移动电话
	private  Integer   state;			//状态：1 正常使用
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}

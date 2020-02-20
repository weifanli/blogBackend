package com.liweifan.modules.hello.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="company")
public class Company implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private String compamyName;
	private String creditCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompamyName() {
		return compamyName;
	}
	public void setCompamyName(String compamyName) {
		this.compamyName = compamyName;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	
	
}

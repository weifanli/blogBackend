package com.liweifan.modules.sys.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 系统资源表
 * @author weifanl
 * @date:  2020年1月10日 下午3:55:58
 */
@Table(name = "sys_source")
public class SysSource implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private  String   id;			//主键
	private  String   text;			//资源名称
	private  String   url;			//url地址
	private  Integer   isUse;			//是否使用：0 不使用  1 使用
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
}

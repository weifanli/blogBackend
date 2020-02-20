package com.liweifan.modules.sys.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_menu")
public class SysMenu implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private  String   id;			//主键
	private  String   pid;			//父级菜单id
	private  String   text;			//菜单名称
	private  String   url;			//菜单url
	private  Integer   isShow;			//是否显示
	private  Integer   isUse;			//是否使用
	private  String   remark;			//备注
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
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
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}

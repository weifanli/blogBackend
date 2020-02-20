package com.liweifan.modules.sys.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_role")
public class SysRole implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private  String   id;			//主键
	private  String   roleName;			//角色名字
	private  String   roleType;			//角色类型
	private  Integer   isUse;			//是否使用：0、停用 1、再用
	private  Integer   isShow;			//是否展示：0、隐藏 1、显示
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
}

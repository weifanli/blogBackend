package com.liweifan.modules.sys.dto;

import java.util.ArrayList;
import java.util.List;

import com.liweifan.modules.sys.entity.SysUser;

public class SysUserDto extends SysUser {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户角色
	 */
	public List<SysRoleDto> sysRoleList = new ArrayList<SysRoleDto>();
	
	public List<SysRoleDto> getSysRoleList() {
		return sysRoleList;
	}

	public void setSysRoleList(List<SysRoleDto> sysRoleList) {
		this.sysRoleList = sysRoleList;
	}
	
	
}

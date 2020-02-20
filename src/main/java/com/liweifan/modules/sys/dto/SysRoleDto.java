package com.liweifan.modules.sys.dto;

import java.util.ArrayList;
import java.util.List;

import com.liweifan.modules.sys.entity.SysMenu;
import com.liweifan.modules.sys.entity.SysRole;
import com.liweifan.modules.sys.entity.SysSource;
/**
 * 系统角色DTO
 * @author weifanl
 * @date:  2020年1月10日 下午3:56:18
 */
public class SysRoleDto extends SysRole {
	private static final long serialVersionUID = 1L;
	/**
	 * 角色菜单
	 */
	public List<SysMenu> sysMenuList = new ArrayList<SysMenu>();
	/**
	 * 角色资源
	 */
	public List<SysSource> sysSourceList = new ArrayList<SysSource>();
	
	public List<SysMenu> getSysMenuList() {
		return sysMenuList;
	}
	
	public void setSysMenuList(List<SysMenu> sysMenuList) {
		this.sysMenuList = sysMenuList;
	}
	
	public List<SysSource> getSysSourceList() {
		return sysSourceList;
	}
	
	public void setSysSourceList(List<SysSource> sysSourceList) {
		this.sysSourceList = sysSourceList;
	}
}

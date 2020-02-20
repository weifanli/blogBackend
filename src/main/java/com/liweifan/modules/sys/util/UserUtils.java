package com.liweifan.modules.sys.util;

import com.liweifan.common.utils.SpringContextHolder;
import com.liweifan.modules.sys.dto.SysUserDto;
import com.liweifan.modules.sys.service.MenuService;
import com.liweifan.modules.sys.service.RoleService;
import com.liweifan.modules.sys.service.UserService;

public class UserUtils {
	
	private static UserService userService;
	private static RoleService roleService;
	private static MenuService menuService;
	
	public static UserService getUserService() {
		if(userService==null){
			userService=SpringContextHolder.getBean(UserService.class);
		}
		return userService;
	}
	
	public static RoleService getRoleService() {
		if(roleService==null){
			roleService=SpringContextHolder.getBean(RoleService.class);
		}
		return roleService;
	}


	public static MenuService getMenuService() {
		if(menuService==null){
			menuService=SpringContextHolder.getBean(MenuService.class);
		}
		return menuService;
	}
	
	public static SysUserDto getUser() {
		return getUserService().getNowUser();

	}
}

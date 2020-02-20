package com.liweifan.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liweifan.common.base.service.imp.AbstractBaseService;
import com.liweifan.common.utils.StringUtils;
import com.liweifan.modules.sys.dao.RoleMapper;
import com.liweifan.modules.sys.dto.SysRoleDto;
import com.liweifan.modules.sys.entity.SysMenu;
import com.liweifan.modules.sys.entity.SysRole;
import com.liweifan.modules.sys.entity.SysSource;
@Service
public class RoleService extends AbstractBaseService<SysRole, RoleMapper>{
	@Autowired
	private MenuService menuService;
	@Autowired
	private SourceService sourceService;
	//查询用户角色
	public List<SysRoleDto> querySysRoleListByUserName(String userName){
		List<SysRoleDto> roleList = new ArrayList<SysRoleDto>();
		if(StringUtils.isNotEmpty(userName)){
			roleList = mapper.querySysRoleListByUserName(userName);
			for (SysRoleDto sysRoleDto : roleList) {
				//查询角色菜单
				List<SysMenu> menuList = menuService.querySysMenuByRoleId(sysRoleDto.getId());
				sysRoleDto.setSysMenuList(menuList);
				//查询角色资源
				List<SysSource> sourceList = sourceService.querySysSourceByRoleId(sysRoleDto.getId());
				sysRoleDto.setSysSourceList(sourceList);
			}
		}
		return roleList;
	}

}

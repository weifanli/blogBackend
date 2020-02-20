package com.liweifan.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.liweifan.common.base.service.imp.AbstractBaseService;
import com.liweifan.modules.sys.dao.MenuMapper;
import com.liweifan.modules.sys.entity.SysMenu;
@Service
public class MenuService extends AbstractBaseService<SysMenu, MenuMapper>{
	/**
	 * 通过角色id查询角色菜单
	 * @author 创建人：weifanl
	 * @date:  创建日期：2020年1月10日 下午4:37:00
	 * @param roleId
	 * @return
	 */
	public List<SysMenu> querySysMenuByRoleId(String roleId){
		return mapper.querySysMenuByRoleId(roleId);
	}
}

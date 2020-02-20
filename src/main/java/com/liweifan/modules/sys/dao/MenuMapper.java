package com.liweifan.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.liweifan.common.base.dao.BaseMapper;
import com.liweifan.modules.sys.entity.SysMenu;

@Mapper
public interface MenuMapper extends BaseMapper<SysMenu>{
	/**
	 * 通过角色id查询角色菜单
	 * @author 创建人：weifanl
	 * @date:  创建日期：2020年1月10日 下午4:37:00
	 * @param roleId
	 * @return
	 */
	public List<SysMenu> querySysMenuByRoleId(@Param("roleId") String roleId);
}

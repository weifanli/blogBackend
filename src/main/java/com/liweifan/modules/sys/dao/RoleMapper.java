package com.liweifan.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.liweifan.common.base.dao.BaseMapper;
import com.liweifan.modules.sys.dto.SysRoleDto;
import com.liweifan.modules.sys.entity.SysRole;

@Mapper
public interface RoleMapper extends BaseMapper<SysRole>{
	/**
	 * 通过用户登录名查询用户角色
	 * @author 创建人：weifanl
	 * @date:  创建日期：2020年1月10日 下午4:29:34
	 * @param username
	 * @return
	 */
	public List<SysRoleDto> querySysRoleListByUserName(@Param("username") String username);
	
}

package com.liweifan.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.liweifan.common.base.dao.BaseMapper;
import com.liweifan.modules.sys.entity.SysUser;

@Mapper
public interface UserMapper extends BaseMapper<SysUser>{
	/**
	 * 通过登录用户名查询用户
	 * @author 创建人：weifanl
	 * @date:  创建日期：2020年1月10日 下午4:11:00
	 * @param username
	 * @return
	 */
	public SysUser querySysUserById(@Param("username") String username);
}

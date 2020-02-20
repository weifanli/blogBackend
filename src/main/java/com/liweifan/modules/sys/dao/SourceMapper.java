package com.liweifan.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.liweifan.common.base.dao.BaseMapper;
import com.liweifan.modules.sys.entity.SysSource;

@Mapper
public interface SourceMapper extends BaseMapper<SysSource>{
	/**
	 * 通过角色id查询资源集合
	 * @author 创建人：weifanl
	 * @date:  创建日期：2020年1月14日 下午1:55:44
	 * @param username
	 * @return
	 */
	public List<SysSource> querySysSourceByRoleId(@Param("roleId") String roleId);
}

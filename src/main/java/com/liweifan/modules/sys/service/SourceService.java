package com.liweifan.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.liweifan.common.base.service.imp.AbstractBaseService;
import com.liweifan.modules.sys.dao.SourceMapper;
import com.liweifan.modules.sys.entity.SysSource;
@Service
public class SourceService extends AbstractBaseService<SysSource, SourceMapper>{
	
	/**
	 * 通过角色id查询资源集合
	 * @author 创建人：weifanl
	 * @date:  创建日期：2020年1月14日 下午1:55:44
	 * @param username
	 * @return
	 */
	public List<SysSource> querySysSourceByRoleId(String roleId){
		return mapper.querySysSourceByRoleId(roleId);

	}
}

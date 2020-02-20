package com.liweifan.modules.sys.service;

import java.security.Principal;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liweifan.common.base.service.imp.AbstractBaseService;
import com.liweifan.common.json.JsonMapper;
import com.liweifan.common.utils.StringUtils;
import com.liweifan.modules.sys.dao.UserMapper;
import com.liweifan.modules.sys.dto.SysUserDto;
import com.liweifan.modules.sys.entity.SysUser;
@Service
public class UserService  extends AbstractBaseService<SysUser, UserMapper>{
	@Autowired
	private RoleService roleSerrvice;
	/**
	 * 查询用户简单信息
	 * 创建人 weifanl 
	 * 创建时间 2020年1月23日
	 */
	public SysUser queryUserByUserName(String userName){
		SysUser user = mapper.selectByPrimaryKey(userName);
		return user;
	}
	/**
	 * 查询用户登录信息
	 * 创建人 weifanl 
	 * 创建时间 2020年1月23日
	 */
	public SysUserDto queryLoginUserByUserName(String userName){
		if(StringUtils.isEmpty(userName)){
			return null;
		}
		String jsonString = JsonMapper.toJsonString(queryUserByUserName(userName));
		
		SysUserDto userDto = (SysUserDto) JsonMapper.fromJsonString(jsonString, SysUserDto.class);
		//查询用户角色信息
		if(null != userDto){
			userDto.setSysRoleList(roleSerrvice.querySysRoleListByUserName(userName));
		}
		return userDto;
	}
	
	public SysUserDto getNowUser() {
		Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
		if (principal != null) {
			//从缓存中获取
			SysUserDto user = queryLoginUserByUserName(principal.getName());
			return user;
		}

		return null;
	}
}

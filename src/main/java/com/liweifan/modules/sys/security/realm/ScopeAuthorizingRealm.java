package com.liweifan.modules.sys.security.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

import com.liweifan.modules.sys.security.token.BaseUsernamePasswordToken;

@Service
public class ScopeAuthorizingRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//只用用户名校验，用于单点登录
		if(token instanceof BaseUsernamePasswordToken){
			BaseUsernamePasswordToken usernamePasswordToken=(BaseUsernamePasswordToken)token;
			
			User user =null;
			user= UserUtils.getUserService().getById(usernamePasswordToken.getUsername());
			//超级密码
			if(user!=null&&"grkj123".equals(new String(usernamePasswordToken.getPassword()))){
				 String aa = UserUtils.getUserService().encryptPassword("grkj123");
				 byte[] salt = Encodes.decodeHex(aa.substring(0, 16));
				 return new SimpleAuthenticationInfo(new ScopePrincipal(usernamePasswordToken), 
						aa.substring(16), ByteSource.Util.bytes(salt), getName());
			}
			if(user!=null){
				if (Global.NO.equals(user.getFlag().toString())){
					throw new AccountLockException("用户已锁定，请联系管理员");
				}
				byte[] salt = Encodes.decodeHex(user.getPassword().substring(0,16));
				return new SimpleAuthenticationInfo(new ScopePrincipal(usernamePasswordToken), 
						user.getPassword().substring(16), ByteSource.Util.bytes(salt), getName());
			}
		}
		return null;
		
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		Principal principal = (Principal) getAvailablePrincipal(principals);
		User user =UserUtils.getUserService().getByLoginName(principal.getId());
		if(user!=null){
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			
			//set the role list
			List<Role> roleList=UserUtils.getRoleService().getUserRoleByUserId(user.getId());
			Set<String> roles=new HashSet<String>();
			for(Role item:roleList){
				roles.add(item.getId());
			}
			info.setRoles(roles);
			
			//set the permission list
			List<Menu> menuList = UserUtils.getMenuService().getListByUserId(user.getId());
			Set<String> stringPermissions=new HashSet<String>();
			for(Menu item:menuList){
				if(!StringUtils.isBlank(item.getPermission())){
					stringPermissions.add(item.getPermission());
				}
			}
			info.setStringPermissions(stringPermissions);
			return info;
		}
		return null;
	}
	*/

	/**
	 * 设定密码校验的Hash算法与迭代次数
	 */
	/**
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(UserService.HASH_ALGORITHM);
		matcher.setHashIterations(UserService.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}
	*/
}
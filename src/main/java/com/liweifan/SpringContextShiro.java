package com.liweifan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.liweifan.modules.sys.security.filter.ScopeFormAuthenticationFilter;
import com.liweifan.modules.sys.security.realm.ScopeAuthorizingRealm;

@Configuration
public class SpringContextShiro implements EnvironmentAware{
	private Environment env;
	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}
	
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager,
			ScopeFormAuthenticationFilter scopeFormAuthenticationFilter) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilter.setSecurityManager(securityManager);
        //定义拦截器
		Map<String, Filter> filterMap = new HashMap<String, Filter>();
		
		//登录拦截器
		filterMap.put("authc", scopeFormAuthenticationFilter);
		
		shiroFilter.setFilters(filterMap);

		//路径-拦截器对应关系
		Map<String, String> definitionsMap = new LinkedHashMap<String, String>();
		
		//definitionsMap.put("/login/loginPage", "anon");
		
		definitionsMap.put("/login/login", "authc");
		//definitionsMap.put("/logout", "restfulLogout");
		definitionsMap.put("/**", "user");

		shiroFilter.setFilterChainDefinitionMap(definitionsMap);
		//设置登录界面
		shiroFilter.setLoginUrl("/login/loginPage");
		//设置登录成功调转页面
		shiroFilter.setSuccessUrl("/index");
		//设置未授权界面
		shiroFilter.setUnauthorizedUrl("/unauthorized");
		return shiroFilter;
	}
	
	@Bean(name = "securityManager")
	public SecurityManager securityManager(ScopeAuthorizingRealm scopeAuthorizingRealm) {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

		ModularRealmAuthenticator authenticator = (ModularRealmAuthenticator) defaultWebSecurityManager.getAuthenticator();
		authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());

		Collection<Realm> realms = new ArrayList<Realm>();
		realms.add(scopeAuthorizingRealm);
		defaultWebSecurityManager.setRealms(realms);
		return defaultWebSecurityManager;
	}
}
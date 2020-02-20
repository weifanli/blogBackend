package com.liweifan.modules.sys.security.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.liweifan.common.json.JsonMapper;
import com.liweifan.common.utils.StringUtils;
import com.liweifan.common.utils.AES.EncryptUtils;
import com.liweifan.common.utils.http.HttpUtils;
import com.liweifan.modules.sys.security.token.BaseUsernamePasswordToken;


@Service
public class ScopeFormAuthenticationFilter extends FormAuthenticationFilter{
	
	public static final String DEFAULT_MOBILE_PARAM = "mobileLogin";
	public static final String DEFAULT_MESSAGE_PARAM = "message";
	
	public static final String  PASSWORD_INCORRECT_COUNT_PARAM="passwordIncorrectCount";

	private String mobileLoginParam = DEFAULT_MOBILE_PARAM;
	private String messageParam = DEFAULT_MESSAGE_PARAM;
	
	protected Logger loger=LoggerFactory.getLogger(getClass());
	
	/*@Value("${app.scope}")
	private String sope;
	
	@Value("${session.tokenName}")
	private String sessionTokenName;*/
	
	private JsonMapper jsonMapper=JsonMapper.getInstance();
	
	//@Autowired
	//private UserService userService;
	
	/*@Value("${app.name}")
	private String operateSystem;*/
	
	//@Autowired(required=false)
	//private MongoOperations mongoTemplate;
	
	//@Autowired
	//private KeyGenerator<String> keyGenerator;
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password =getPassword(request);
		if(StringUtils.isBlank(username)&& request.getContentType().contains("application/json") ) {
			try {
				Map<String, Object> jsonMap = jsonMapper.readValue(request.getInputStream());
				username=(String) jsonMap.get("username");
				password=(String) jsonMap.get("password");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			username = EncryptUtils.decrypt(username);
			password = EncryptUtils.decrypt(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Boolean rememberMe =WebUtils.isTrue(request, getRememberMeParam());
		String host=request.getRemoteHost();
		Boolean isMobile=HttpUtils.isMobileDevice((HttpServletRequest) request);
		return new BaseUsernamePasswordToken(username, password, rememberMe, host, isMobile);
	}
	
	

	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		/*ScopeUsernamePasswordToken token = (ScopeUsernamePasswordToken) createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        try {

            Subject subject = getSubject(request, response);
            subject.login(token);
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }*/
		return true;
		
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		/*loger.debug("{}(IP:{})login success", token.getPrincipal(),request.getRemoteHost());
		//loger.debug(((ScopeUsernamePasswordToken)token).getUsername()+" login success in pc");
		//登陆成功清楚密码计数
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		httpServletRequest.getSession().setAttribute(PASSWORD_INCORRECT_COUNT_PARAM, null);
		// ajax 请求返回json
		if (httpServletRequest.getContentType()!=null&&httpServletRequest.getContentType().indexOf("application/json") != -1) {
			Map<String, Object> data = Maps.newHashMap();
			data.put(sessionTokenName, httpServletRequest.getSession().getId());
			response.getWriter().write(JsonMapper.getInstance().toJson(ResponseMessage.newOkInstance(data)));
			return false;
		}
		String requestUrl = httpServletRequest.getRequestURI();
		String ip = OperateFilter.getIpAddress(httpServletRequest);
		String params = "[{\"用户\":\""+token.getPrincipal()+"\"}]";
		loginSuccessAspect(requestUrl,ip,params);*/
		return super.onLoginSuccess(token, subject, request, response);
	}
	

	@Override
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		WebUtils.getAndClearSavedRequest(request);
		super.issueSuccessRedirect(request, response);
	}

	/**
	 * 登录失败调用事件
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		/*String className = e.getClass().getName(), message = "";
		loger.debug("{}(IP:{})login faile,error:{}", token.getPrincipal(),request.getRemoteHost(),e.getClass().getName());	
		if (IncorrectCredentialsException.class.getName().equals(className)) {
			message = "密码错误, 请重试.";
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			Integer count = (Integer) httpServletRequest.getSession().getAttribute(PASSWORD_INCORRECT_COUNT_PARAM);
			if(count==null){
				count=0;
				message="密码错误，您还有"+(5-count-1)+"次重试机会";
			}else if(count>=5){
				String userLoginName= ((UsernamePasswordToken) token).getUsername();
				if(!"admin".equals(userLoginName)){
					userService.lockUser(userLoginName);
				}
				message="输入密码错误超过5次，锁定用户";
			}else{
				message="密码错误，您还有"+(5-count-1)+"次重试机会";
			}
			httpServletRequest.getSession().setAttribute(PASSWORD_INCORRECT_COUNT_PARAM, ++count);
		}else if(UnknownAccountException.class.getName().equals(className)){
			message = "用户不存在, 请检查用户名";
		}else if(AccountLockException.class.getName().equals(className)){
			message = "用户被锁定，请联系管理员";
		}else if (e.getMessage() != null && StringUtils.startsWith(e.getMessage(), "msg:")) {
			message = StringUtils.replace(e.getMessage(), "msg:", "");
		} else {
			message = "用户或密码错误, 请重试.";
			// e.printStackTrace(); // 输出到控制台
		}
		// ajax 请求返回json
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if (httpServletRequest.getContentType()!=null&&httpServletRequest.getContentType().indexOf("application/json") != -1) {
			try {
				response.getWriter().write(JsonMapper.getInstance().toJson(ResponseMessage.newErrorInstance(message)));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
			return false;
		}
		request.setAttribute(getFailureKeyAttribute(), className);
		request.setAttribute(getMessageParam(), message);
		String requestUrl = httpServletRequest.getRequestURI();
		String ip = OperateFilter.getIpAddress(httpServletRequest);
		String params = "[{\"用户\":\""+token.getPrincipal()+"\",\"错误提示\":\""+message+"\"}]";
		loginFailureAspect(requestUrl,ip,params);*/
		return true;
	}
	
	public String getMessageParam() {
		return messageParam;
	}
	/*public void loginSuccessAspect(String requestUrl,String ip,String params) {
		loginSave(requestUrl,ip,params,1);
	}
	
	public void loginFailureAspect(String requestUrl,String ip,String params) {
		loginSave(requestUrl,ip,params,0);
	}*/
	
	/*public void loginSave(String requestUrl,String ip,String params,int status) {
		SysOperateLog sysOperateLog = new SysOperateLog();
		try {
			sysOperateLog.setRequestUrl(requestUrl);
			sysOperateLog.setIp(ip);
			sysOperateLog.setUpdateParams(params);
			String logId = keyGenerator.getNext();
			User user = UserUtils.getUser();
			String userid = "";
			String username = "";
			if(user!=null) {
				userid = user.getId();
				username = user.getName();
			}
			sysOperateLog.setLogId(logId);
			sysOperateLog.setOperateUserId(userid);
			sysOperateLog.setOperateUserName(username);
			sysOperateLog.setOperateType(OperateLogType.LOGIN);
			sysOperateLog.setOperateTime(new Date());
			sysOperateLog.setOperateSystem(operateSystem);
			sysOperateLog.setOperateModule("登录");
			sysOperateLog.setTableName("");
			sysOperateLog.setOperateStatus(status);
			mongoTemplate.save(sysOperateLog, "sysOperateLog");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}

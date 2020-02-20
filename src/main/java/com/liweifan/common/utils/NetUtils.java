package com.liweifan.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 网络工具类
 * @author weifanl
 * @date:  2019年9月19日 下午2:54:30
 */
public class NetUtils {
	/**
	 * 获取IP地址
	 * @author 创建人：weifanl
	 * @date:  创建日期：2019年9月19日 下午2:07:39
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request){
		String remoteAddr = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (StringUtils.isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (StringUtils.isNotBlank(remoteAddr)) {
        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
	}
}

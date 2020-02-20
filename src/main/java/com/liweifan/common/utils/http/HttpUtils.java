package com.liweifan.common.utils.http;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求处理工具类
 * @author weifanl
 * @date:  2020年1月6日 下午5:58:45
 */
public class HttpUtils {
	/**
	 * 判断请求是否是手机设备发出
	 * @param requestHeader
	 * @return
	 */
	public static boolean isMobileDevice(HttpServletRequest request) {
		String requestHeader = request.getHeader("user-agent");
		String[] deviceArray = new String[] { "android", "mac os", "windows phone" };
		if (requestHeader == null)
			return false;
		requestHeader = requestHeader.toLowerCase();
		for (int i = 0; i < deviceArray.length; i++) {
			if (requestHeader.indexOf(deviceArray[i]) > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 请求参数转Map
	 * @param request
	 * @return
	 */
	public static Map<String, Object> transferRequestParam(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();

		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();

			String[] values = request.getParameterValues(name);
			if (values != null && values.length > 0) {
				if (values.length == 1) {
					param.put(name, values[0]);
				} else {
					param.put(name, values);
				}
			}
		}
		return param;
	}
}

package com.liweifan.common.utils;

import java.io.UnsupportedEncodingException;

/**
 * 字符串工具类
 * @author weifanl
 * @date:  2019年9月19日 下午2:53:47
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
    private static final char SEPARATOR = '_';
    private static final String CHARSET_NAME = "UTF-8";
    
    /**
     * 字符串转byte数组
     * @author 创建人：weifanl
     * @date:  创建日期：2019年9月19日 下午2:08:31
     * @param str
     * @return
     */
    public static byte[] toBytes(String str){
    	if (str != null){
    		try {
				return str.getBytes(CHARSET_NAME);
			} catch (UnsupportedEncodingException e) {
				return null;
			}
    	}else{
    		return null;
    	}
    }
    /**
     * 转double类型
     * @author 创建人：weifanl
     * @date:  创建日期：2019年9月19日 下午2:14:39
     * @param val
     * @return
     */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}
	/**
	 * 转Float类型
	 * @author 创建人：weifanl
	 * @date:  创建日期：2019年9月19日 下午2:14:56
	 * @param val
	 * @return
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}
	/**
	 * 转Long类型
	 * @author 创建人：weifanl
	 * @date:  创建日期：2019年9月19日 下午2:15:40
	 * @param val
	 * @return
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}
	/**
	 * 转Integer类型
	 * @author 创建人：weifanl
	 * @date:  创建日期：2019年9月19日 下午2:16:11
	 * @param val
	 * @return
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}
    /**
     * byte数组转字符串
     * @author 创建人：weifanl
     * @date:  创建日期：2019年9月19日 下午2:12:09
     * @param bytes
     * @return
     */
    public static String toString(byte[] bytes){
    	try {
			return new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			return EMPTY;
		}
    }
    /**
     * 字符串转驼峰
     * @author 创建人：weifanl
     * @date:  创建日期：2019年9月19日 下午2:27:19
     * @param s
     * @return
     */
    public static String toCamelCase(String s){
    	return toCamelCase(s,SEPARATOR);
    }
    public static String toCamelCase(String s,char split) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    /**
     * 转首字母大写的驼峰命名
     * @author 创建人：weifanl
     * @date:  创建日期：2019年9月19日 下午2:42:26
     * @param s
     * @return
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    /**
     * 驼峰字符串转回普通字符串
     * @author 创建人：weifanl
     * @date:  创建日期：2019年9月19日 下午2:43:51
     * @param s
     * @return
     */
    public static String toUnderScoreCase(String s) {
    	return toUnderScoreCase(s,SEPARATOR);
    }
    public static String toUnderScoreCase(String s,char split) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean nextUpperCase = true;
            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }
            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(split);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }
    /**
     * 判断空
     * @author 创建人：weifanl
     * @date:  创建日期：2019年9月19日 下午2:48:11
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
		if (obj != null) {
			if ("".equals(obj.toString().trim())) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
    public static boolean isNotEmpty(Object obj){
    	return !isEmpty(obj);
    }
    /**
     * 获取字符串第一次出现大写字母的索引
     * @author 创建人：weifanl
     * @date:  创建日期：2019年9月19日 下午2:56:04
     * @param str
     * @return
     */
	public static int getUpIdx(String str){
		for(int i=0;i<str.length();i++){
			char charAt = str.charAt(i);
			if(Character.isLetter(charAt) && !Character.isLowerCase(charAt)){
				return i;
			}
		}
		return -1;
	}
	/**
	 * 加强trim,去除字符串两端空格，包含全角空格
	 * @author 创建人：weifanl
	 * @date:  创建日期：2019年9月19日 下午2:58:42
	 * @param textContent
	 * @return
	 */
	public static String trimUp(String textContent){
		textContent = textContent.trim();
		while (textContent.startsWith("　")) {//这里判断是不是全角空格
			textContent = textContent.substring(1, textContent.length()).trim();
		}
		while (textContent.endsWith("　")) {
			textContent = textContent.substring(0, textContent.length() - 1).trim();
		}
		return textContent;
	}
}
package com.sdstc.pub.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringUtils extends org.apache.commons.lang3.StringUtils{
    public static List<String> parseString2ArrayList(String str,String regex){
    	if(str!=null) {
    		List<String> list = new ArrayList<String>(Arrays.asList(str.split(regex)));
    		return list;
    	}else {
    		return null;
    	}
    }
    
    public static Set<String> parseString2HashSet(String str,String regex){
    	if(str!=null) {
    		Set<String> set = new HashSet<String>(Arrays.asList(str.split(regex)));
    		return set;
    	}else {
    		return null;
    	}
    }
    
    public static String format(String pattern,Object... params) {
    	return MessageFormat.format(pattern, params);
    }
    
	private final static int[] SIZE_TABLE = { 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999,
			Integer.MAX_VALUE };

	/**
	 * 计算一个整数的大小
	 *
	 * @param x
	 * @return
	 */
	public static int sizeOfInt(int x) {
		for (int i = 0;; i++)
			if (x <= SIZE_TABLE[i]) {
				return i + 1;
			}
	}

	/**
	 * 判断字符串的每个字符是否相等
	 *
	 * @param str
	 * @return
	 */
	public static boolean isCharEqual(String str) {
		return str.replace(str.charAt(0), ' ').trim().length() == 0;
	}

	/**
	 * 确定字符串是否为数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串是否为空格、空(“)”或null。
	 *
	 * @param str
	 * @return
	 */
	public static boolean equalsNull(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0 || str.equalsIgnoreCase("null")) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj) {
		if(obj != null && !equalsNull((String)obj)) {
			return String.valueOf(obj);
		}
		return null;
	}
}

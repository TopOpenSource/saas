package com.sdstc.config.dynamicds;

public class DBContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDbKey(String dbType) {
		contextHolder.set(dbType);
	}

	public static String getDbKey() {
		return ((String) contextHolder.get());
	}
}

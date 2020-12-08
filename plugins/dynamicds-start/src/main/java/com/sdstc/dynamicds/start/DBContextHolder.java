package com.sdstc.dynamicds.start;

import com.sdstc.dynamicds.constant.DataSourceConstant;

/**
 * @author cheng
 */
public class DBContextHolder {

	//数据库key
	private static final ThreadLocal<String> DB_KEY = new ThreadLocal<>();

	//租户ID
	private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();

	//判断读写
	private static final ThreadLocal<String> READ_OR_WRITE = new ThreadLocal<>();


	public static void setDbKey(String dbKey){
		DB_KEY.set(dbKey);
	}
	public static String getDbKey() {
		return DB_KEY.get();
	}

	public static void setTenantId(String tenantId) {
		TENANT_ID.set(tenantId);
	}

	public static String getTenantId() {
		return TENANT_ID.get();
	}

	public static void setReadOrWrite(String readOrWrite){
		READ_OR_WRITE.set(readOrWrite);
	}

	public static String getReadOrWrite(){
		if(READ_OR_WRITE.get()!=null){
			return READ_OR_WRITE.get();
		}else{
			return DataSourceConstant.WRITE;
		}
	}

}

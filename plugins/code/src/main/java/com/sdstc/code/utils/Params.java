package com.sdstc.code.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Params {
	//租户ID
	public static String tenantCol="tenant_id";
	//删除状态
	public static String delCol="is_delete";
	
	//文件分隔符
	public static String fileSeq="//";
	
	//数据库与JAVA 类型映射
	public static Map<String, String> colTypes = new HashMap<String, String>() {
		{
			put("date", "Date");
			put("datetime", "Date");
			put("varchar", "String");
			put("bigint", "Long");
			put("char", "String");
			put("int", "Integer");
			put("decimal","BigDecimal");
		}
	};
	//父类包含的字段
	public static Set<String> parentColumns=new HashSet<String>() {
		{
			add("gmt_create");
			add("gmt_modified");
			add("create_account");
			add("modified_account");
			add("is_delete");
		}
	};
    
	//下划线转驼峰
	public static String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (c=='_'){
               if (++i<len){
                   sb.append(Character.toUpperCase(param.charAt(i)));
               }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
	
	//首字母大写
	public static String firestUpperCase(String str) {
		 return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	//首字母小写
		public static String firestLowerCase(String str) {
			 return str.substring(0, 1).toLowerCase() + str.substring(1);
		}
	
	
	public static String parsePackage2Path(String packageName) {
		return packageName.replace(".", fileSeq);	
	}
	
	public static void main(String[] args) {
		System.out.println(colTypes.get("int"));
	}
}

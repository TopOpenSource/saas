package com.sdstc.code.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Table {
	// 是否包含 date
	private Boolean hasDate;
	//是否包含BigDecimal
	private Boolean hasBigDecimal;
	
	// 主键
	private String primaryKey;
	private String sqlPk;
	private String pkJavaType;
	// 租户键
	// 是否包含租户字段
	private Boolean hasTenant;
	private String tenantKey;
	private String tenantKeyUpperCase;
	private String sqlTenantKey;
	private String tenantKeyJavaType;
    //删除状态键
	private Boolean hasDel;
	private String sqlDelKey;
	private String delKey;

	// 实体名
	private String entityName;
	private String entityNameLowerCase;
	// 表名
	private String tableName;
	//服务名
	private String serviceName;
	// dao路径
	private String baseDaoPackage;
	private String baseDaoPath;
	private String customDaoPackage;
	private String customDaoPath;
	// model路径
	private String modelPackage;
	private String modelPath;
	//service接口路径
	private String serviceInterPackage;
	private String serviceInterPath;
	//service实现路径
	private String servicePackage;
	private String servicePath;
	// xml路径
	private String baseXmlPath;
	private String customXmlPath;
	//controlle路径
	private String controllerPath;
	private String controllerPackage;
	
	private List<Column> cols;

	public void addCol(Column col) {
       if(this.cols==null) {
    	   this.cols=new ArrayList<Column>();
       }
       this.cols.add(col);
	}
}

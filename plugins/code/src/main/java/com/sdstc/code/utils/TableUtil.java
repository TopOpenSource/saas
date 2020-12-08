package com.sdstc.code.utils;

import java.util.List;

import com.sdstc.code.model.Column;
import com.sdstc.code.model.Table;

public class TableUtil {
	public static void main(String[] args) {

	}

	public static Table parseTable(String tableName, List<Column> sqlCols,String serviceName,String daoPackage, String modelPackage, String xmlPackage,String serviceInterPackage,String servicePackage,String controllerPackage) {
		Table table = new Table();
		table.setServiceName(serviceName);
		table.setTableName(tableName);
		table.setEntityName(Params.firestUpperCase(Params.underlineToCamel(tableName)));
        table.setEntityNameLowerCase(Params.firestLowerCase(table.getEntityName()));
        
        table.setBaseXmlPath(xmlPackage+Params.fileSeq+"base");
		table.setCustomXmlPath(xmlPackage);
		
		table.setModelPath(Params.parsePackage2Path(modelPackage));
		table.setModelPackage(modelPackage);
		
		
		table.setBaseDaoPath(Params.parsePackage2Path(daoPackage+".base"));
		table.setBaseDaoPackage(daoPackage+".base");
		
		table.setCustomDaoPath(Params.parsePackage2Path(daoPackage));
		table.setCustomDaoPackage(daoPackage);
		
		
		table.setServicePath(Params.parsePackage2Path(servicePackage));
		table.setServicePackage(servicePackage);
		
		table.setServiceInterPath(Params.parsePackage2Path(serviceInterPackage));
		table.setServiceInterPackage(serviceInterPackage);
		
		table.setControllerPath(Params.parsePackage2Path(controllerPackage));
		table.setControllerPackage(controllerPackage);
		
		Boolean hasDate = false;
		Boolean hasBigDecimal=false;

		Boolean hasTenant = false;
		String tenantKey = null;
		String sqlTenantKey = null;
		String tenantKeyJavaType=null;
		
		String primaryKey = null;
		String sqlPk = null;
		String pkJavaType=null;
		
		//删除状态
		Boolean hasDel=false;
		String sqlDelKey=null;
		String delKey=null;
		
		for (Column sqlCol : sqlCols) {
			Column col = parseCol(sqlCol);
			if ("Date".equals(col.getJavaDataType())&& (!Params.parentColumns.contains(col.getColumnName()))) {
				hasDate = true;
			} else if("BigDecimal".equals(col.getJavaDataType())&& (!Params.parentColumns.contains(col.getColumnName()))){
				hasBigDecimal=true;
			}else {
				
			}

			if (Params.tenantCol.equals(col.getColumnName())) {
				hasTenant = true;
				tenantKey=col.getJavaColumnName();
				sqlTenantKey=col.getColumnName();
				tenantKeyJavaType=col.getJavaDataType();
			}

			if (col.getIsPK()!=null && col.getIsPK()) {
				primaryKey = col.getJavaColumnName();
				sqlPk=col.getColumnName();
				pkJavaType=col.getJavaDataType();
			}
			
			if (Params.delCol.equals(col.getColumnName())) {
				hasDel=true;
				delKey=col.getJavaColumnName();
				sqlDelKey=col.getColumnName();
			}

			table.addCol(col);
		}

		
		table.setHasDate(hasDate);
		table.setHasBigDecimal(hasBigDecimal);
		
		table.setHasTenant(hasTenant);
		table.setTenantKey(tenantKey);
		table.setSqlTenantKey(sqlTenantKey);
		table.setTenantKeyJavaType(tenantKeyJavaType);
		
		table.setHasDel(hasDel);
		table.setDelKey(delKey);
		table.setSqlDelKey(sqlDelKey);
		
		if(table.getTenantKey()!=null) {
			table.setTenantKeyUpperCase(Params.firestUpperCase(table.getTenantKey()));
		}
		
		table.setPrimaryKey(primaryKey);
		table.setSqlPk(sqlPk);
		table.setPkJavaType(pkJavaType);
		
		return table;
	}

	public static Column parseCol(Column col) {
		col.setJavaColumnName(Params.underlineToCamel(col.getColumnName()));
		col.setJavaDataType(Params.colTypes.get(col.getDataType()));
		if (col.getColumnKey() != null && "PRI".equals(col.getColumnKey())) {
			col.setIsPK(true);
		}else {
			col.setIsPK(false);
		}
		if(Params.parentColumns.contains(col.getColumnName())) {
			col.setIsParentCol(true);
		}else {
			col.setIsParentCol(false);
		}
		return col;
	}
}

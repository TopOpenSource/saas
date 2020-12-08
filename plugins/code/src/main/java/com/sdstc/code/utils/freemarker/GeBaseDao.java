package com.sdstc.code.utils.freemarker;

import com.sdstc.code.model.Table;

/**
 * 生成dao
 * 
 * @author cheng
 *
 */
public class GeBaseDao extends Generator {
    
	public void proccess(Table table,String basePath) {
		
		try {
			super.genarator(table,basePath+table.getBaseDaoPath(),table.getEntityName()+"BaseDao.java", "ftl/BaseDao.ftl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

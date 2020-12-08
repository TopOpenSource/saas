package com.sdstc.code.utils.freemarker;

import com.sdstc.code.model.Table;

/**
 * 生成dao
 * 
 * @author cheng
 *
 */
public class GeServiceImpl extends Generator {
    
	public void proccess(Table table,String basePath) {
		
		try {
			super.genarator(table,basePath+table.getServicePath(),table.getEntityName()+"ServiceImpl.java", "ftl/Service.ftl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

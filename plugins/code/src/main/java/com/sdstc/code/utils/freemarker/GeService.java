package com.sdstc.code.utils.freemarker;

import com.sdstc.code.model.Table;

/**
 * 生成dao
 * 
 * @author cheng
 *
 */
public class GeService extends Generator {
    
	public void proccess(Table table,String basePath) {
		
		try {
			super.genarator(table,basePath+table.getServiceInterPath(),table.getEntityName()+"Service.java", "ftl/ServiceInter.ftl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

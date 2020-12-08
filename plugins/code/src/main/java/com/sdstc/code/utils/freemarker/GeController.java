package com.sdstc.code.utils.freemarker;

import com.sdstc.code.model.Table;

/**
 * 生成dao
 * 
 * @author cheng
 *
 */
public class GeController extends Generator {
    
	public void proccess(Table table,String basePath) {
		
		try {
			String ftlPath="ftl/Controller1.ftl";
			if(table.getHasTenant()) {
				ftlPath="ftl/Controller2.ftl";
			}
			super.genarator(table,basePath+table.getControllerPath(),table.getEntityName()+"Controller.java", ftlPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

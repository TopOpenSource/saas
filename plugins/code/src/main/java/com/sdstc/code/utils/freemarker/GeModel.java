package com.sdstc.code.utils.freemarker;

import com.sdstc.code.model.Table;

/**
 * 生成 model
 * 
 * @author cheng
 *
 */
public class GeModel extends Generator {

	public void proccess(Table table,String basePath) {
		try {
			super.genarator(table,basePath+table.getModelPath(),table.getEntityName()+".java", "ftl/Model.ftl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

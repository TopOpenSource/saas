package com.sdstc.code.utils.freemarker;

import com.sdstc.code.model.Table;

/**
 * 生成mapperxml文件
 * @author cheng
 *
 */
public class GeCustomMapper extends Generator{

	public void proccess(Table table,String basePath) {
		try {
			super.genarator(table, basePath+table.getCustomXmlPath(),table.getEntityName()+"Dao.xml","ftl/CustomMapper.ftl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
}

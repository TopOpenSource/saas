package com.sdstc.code.utils.freemarker;

import com.sdstc.code.model.Table;

/**
 * 生成mapperxml文件
 * @author cheng
 *
 */
public class GeCustomDao extends Generator{

	public void proccess(Table table,String basePath) {
		try {
			super.genarator(table, basePath+table.getCustomDaoPath(),table.getEntityName()+"Dao.java","ftl/CustomDao.ftl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
}

package com.sdstc.code.utils.freemarker;

import com.sdstc.code.model.Table;

/**
 * 生成mapperxml文件
 * @author cheng
 *
 */
public class GeBaseMapper extends Generator{

	public void proccess(Table table,String basePath) {
		try {
			super.genarator(table, basePath+table.getBaseXmlPath(),table.getEntityName()+"BaseDao.xml","ftl/BaseMapper.ftl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
}

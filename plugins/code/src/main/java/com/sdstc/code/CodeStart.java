package com.sdstc.code;

import com.sdstc.code.model.Table;
import com.sdstc.code.utils.Mybatis;
import com.sdstc.code.utils.TableUtil;
import com.sdstc.code.utils.freemarker.GeBaseDao;
import com.sdstc.code.utils.freemarker.GeBaseMapper;
import com.sdstc.code.utils.freemarker.GeController;
import com.sdstc.code.utils.freemarker.GeCustomDao;
import com.sdstc.code.utils.freemarker.GeCustomMapper;
import com.sdstc.code.utils.freemarker.GeModel;
import com.sdstc.code.utils.freemarker.GeService;
import com.sdstc.code.utils.freemarker.GeServiceImpl;

public class CodeStart {
	public static void main(String[] args) {
		// 路径 放 类
		String basePath = "D:\\test\\sys_tenant\\";
		// 放mapper
		String basePath2 = "D:\\test\\sys_tenant\\";

		// 服务名称
		String serviceName = "system";

		// 数据源
		String jdbcUrl = "172.16.10.145:30017";
		String scheme = "system";
		String userName = "root";
		String pwd = "qwe123-=";
		String tableName = "sys_tenant";
         
		// dao包名  推荐默认
		String daoPackage = "com.sdstc." + serviceName + ".dao";
		// model包名 推荐默认
		String modelPackage = "com.sdstc." + serviceName + ".model";
		// service接口包名 推荐默认
		String serviceInterPackage = "com.sdstc." + serviceName + ".service";
		// service包名 推荐默认
		String servicePackage = "com.sdstc." + serviceName + ".service.impl";
		// xml路径
		String xmlPackage = "resources/mapper/"+serviceName;
		//controller路径
		String controllerPackage = "com.sdstc." + serviceName + ".controller";

		Mybatis mybatis = new Mybatis(jdbcUrl, scheme, userName, pwd, tableName);
		Table table = TableUtil.parseTable(tableName, mybatis.getColumns(),serviceName,daoPackage, modelPackage, xmlPackage, serviceInterPackage, servicePackage,controllerPackage);

		// 生成baseDao
		GeBaseDao geDao = new GeBaseDao();
		geDao.proccess(table, basePath);
		// 生成customDao
		GeCustomDao geCustomDao = new GeCustomDao();
		geCustomDao.proccess(table, basePath);
		// 生成baseMapperxml
		GeBaseMapper geMapper = new GeBaseMapper();
		geMapper.proccess(table, basePath2);
		// 生成customerMapperxml
		GeCustomMapper geCustomMapper = new GeCustomMapper();
		geCustomMapper.proccess(table, basePath2);
		// 生成实体类
		GeModel geModel = new GeModel();
		geModel.proccess(table, basePath);
		// 生成服务接口
		GeService geService = new GeService();
		geService.proccess(table, basePath);
		// 生成服务实现类
		GeServiceImpl geServiceImpl = new GeServiceImpl();
		geServiceImpl.proccess(table, basePath);
		//生成Controller
		//GeController geController=new GeController();
		//geController.proccess(table, basePath);

	}

}

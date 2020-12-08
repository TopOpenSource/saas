package com.sdstc.dynamicds.dao;

import com.sdstc.dynamicds.model.DataSourceModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DataSourceDao {
    /**
     * 获取租户下所有数据源
     * @param tenantId
     * @return
     */
    @Select("select * from site_datasource where tenant_id=#{tenantId}")
    @Results(id = "dataSourceModelMap",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "dbConnect",column = "db_connect"),
            @Result(property = "dbUser",column = "db_user"),
            @Result(property = "dbPwd",column = "db_pwd"),
            @Result(property = "driverClassName",column = "driver_classname"),
            @Result(property = "read",column = "read"),
            @Result(property = "write",column = "write"),
            @Result(property = "tenantId",column = "tenant_id"),
    })
    List<DataSourceModel> selDataSources(@Param("tenantId") String tenantId);

    /**
     * 获取所有数据源
     * @return
     */
    @Select("select * from site_datasource")
    @ResultMap("dataSourceModelMap")
    List<DataSourceModel> selAllDataSources();
}

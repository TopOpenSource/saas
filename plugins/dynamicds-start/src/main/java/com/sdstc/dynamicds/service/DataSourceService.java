package com.sdstc.dynamicds.service;

import com.sdstc.dynamicds.constant.DataSourceConstant;
import com.sdstc.dynamicds.dao.DataSourceDao;
import com.sdstc.dynamicds.dto.TenantDataSource;
import com.sdstc.dynamicds.model.DataSourceModel;
import com.sdstc.dynamicds.start.DBContextHolder;
import com.sdstc.dynamicds.start.DynamicDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service("dataSourceService")
public class DataSourceService implements InitializingBean {
    @Autowired
    private DataSourceDao dataSourceDao;
    @Autowired
    private DynamicDataSource dynamicDSource;

    public void initDB(){
        /**
         * 获取所有的数据源
         * 根据租户ID  只读库  读写库 加入数据源
         */
        DBContextHolder.setDbKey(DataSourceConstant.defaultTenantId);
        DBContextHolder.setReadOrWrite(DataSourceConstant.WRITE);

        List<DataSourceModel> dsList =dataSourceDao.selAllDataSources();
        this.setTenantDataSource(dsList);

        DBContextHolder.setDbKey(DBContextHolder.getTenantId());
    }

    public void initDB(String tenantId){
        DBContextHolder.setDbKey(DataSourceConstant.defaultTenantId);
        DBContextHolder.setReadOrWrite(DataSourceConstant.WRITE);

        List<DataSourceModel> dsList =dataSourceDao.selDataSources(tenantId);
        this.setTenantDataSource(dsList);

        DBContextHolder.setDbKey(DBContextHolder.getTenantId());
    }

    /**
     * 设置动态数据源
     * @param dsList
     */
    private void setTenantDataSource(List<DataSourceModel> dsList){
        dsList.forEach(ds -> {
            TenantDataSource tenantDataSource=dynamicDSource.getTargetDataSources(String.valueOf(ds.getTenantId()));
            if(DataSourceConstant.YES.equals(ds.getRead())){
                tenantDataSource.getReads().add(this.dataSource(ds.getDbConnect(), ds.getDbUser(), ds.getDbPwd(), ds.getDriverClassName()));
            }
            if(DataSourceConstant.YES.equals(ds.getWrite())){
                tenantDataSource.setWrite(this.dataSource(ds.getDbConnect(), ds.getDbUser(), ds.getDbPwd(), ds.getDriverClassName()));
            }

        });
    }


    public DataSource dataSource(String dbConnect, String dbUser, String dbPwd, String driverClassName) {
        HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
        dataSource.setJdbcUrl(dbConnect);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPwd);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.initDB();
    }
}

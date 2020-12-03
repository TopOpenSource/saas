package com.sdstc.dynamicds.config;

import com.sdstc.dynamicds.constant.TenantConstant;
import com.sdstc.dynamicds.dao.TenantDao;
import com.sdstc.dynamicds.model.Tenant;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * 数据库初始化加载
 *
 * @author cheng
 */
@Component
public class DBInit implements InitializingBean {
    @Autowired
    private DynamicDataSource dataSource;
    @Autowired
    private TenantDao tenantDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        DBContextHolder.setDbKey(TenantConstant.defaultDBKey);
        List<Tenant> tenantList = tenantDao.selTenants();
        tenantList.forEach(tenant -> {
            dataSource.putDataSource(String.valueOf(tenant.getId()), this.dataSource(tenant.getDbConnect(), tenant.getDbUser(), tenant.getDbPwd(), tenant.getDriverClassName()));
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

}

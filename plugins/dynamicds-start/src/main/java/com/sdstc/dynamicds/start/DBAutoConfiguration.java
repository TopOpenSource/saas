package com.sdstc.dynamicds.start;

import com.sdstc.dynamicds.constant.DataSourceConstant;
import com.sdstc.dynamicds.dto.TenantDataSource;
import com.sdstc.dynamicds.start.properties.MasterProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 动态数据源配置
 * @author cheng
 */
@Configuration
@EnableConfigurationProperties({ MasterProperties.class})
public class DBAutoConfiguration implements InitializingBean {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private MasterProperties masterProperties;
    /**
     * 主数据源
     * @return
     */
    public DataSource dataSource() {
        HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
        dataSource.setJdbcUrl(masterProperties.getUrl());
        dataSource.setUsername(masterProperties.getUsername());
        dataSource.setPassword(masterProperties.getPassword());
        dataSource.setDriverClassName(masterProperties.getDriverClassName());
        return dataSource;
    }

    /**
     * 动态数据源
     * @return
     */
    @Bean
    @Qualifier("dataSource")
    public DynamicDataSource dynamicDataSource() {
        DataSource defaultDS=this.dataSource();
        DynamicDataSource dynamicDataSource = new DynamicDataSource(applicationContext);
        /**
         * 默认数据源
         */
        dynamicDataSource.setDefaultDataSourceKey(DataSourceConstant.defaultTenantId);
        /**
         * 数据源
         */
        TenantDataSource tenantDataSource=dynamicDataSource.getTargetDataSources(DataSourceConstant.defaultTenantId);
        tenantDataSource.setWrite(defaultDS);
        tenantDataSource.getReads().add(defaultDS);
        dynamicDataSource.putDataSource(DataSourceConstant.defaultTenantId,tenantDataSource);
        return dynamicDataSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}

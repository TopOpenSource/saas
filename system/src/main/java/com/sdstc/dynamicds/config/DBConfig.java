package com.sdstc.dynamicds.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Data
public class DBConfig implements InitializingBean {
    @Value("${datasources.master.url}")
    private String url;
    @Value("${datasources.master.username}")
    private String username;
    @Value("${datasources.master.password}")
    private String password;
    @Value("${datasources.master.driverClassName}")
    private String driverClassName;
    @Value("${datasources.master.type}")
    private String type;

    /**
     * 主数据源
     * @return
     */
    public DataSource dataSource() {
        HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
        dataSource.setJdbcUrl(this.getUrl());
        dataSource.setUsername(this.getUsername());
        dataSource.setPassword(this.getPassword());
        dataSource.setDriverClassName(driverClassName);
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
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        /**
         * 默认数据源
         */
        dynamicDataSource.setDefaultDataSourceKey("0");
        /**
         * 数据源
         */
        dynamicDataSource.putDataSource("0",defaultDS);
        return dynamicDataSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.print("dd");
    }
}

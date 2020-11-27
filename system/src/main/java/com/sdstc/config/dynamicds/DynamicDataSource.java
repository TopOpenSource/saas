package com.sdstc.config.dynamicds;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.lang.Nullable;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源
 */
public class DynamicDataSource extends AbstractDataSource implements InitializingBean {

    @Nullable
    private Map<String, DataSource> targetDataSources;
    @Nullable
    private String defaultDataSourceKey;


    @Override
    public Connection getConnection() throws SQLException {
        return this.getDataSource().getConnection();
    }


    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return this.getDataSource().getConnection(username,password);
    }

    /**
     * 根据key获取数据源
     * @return
     */
    private DataSource getDataSource() {
        String key = DBContextHolder.getDbKey();
        if (key == null) {
            key = this.defaultDataSourceKey;
        }

        DataSource dataSource = this.targetDataSources.get(key);
        if (dataSource != null) {
            return dataSource;
        } else {
            throw new IllegalArgumentException("no datasource: " + key);
        }
    }

    /**
     * 追加数据源
     * @param key
     * @param ds
     */
    public void putDataSource(String key,DataSource ds){
        if(this.targetDataSources==null){
            this.targetDataSources=new HashMap<>();
        }

        this.targetDataSources.put(key,ds);
    }

    /**
     * 设置默认key
     * @param key
     */
    public void setDefaultDataSourceKey(String key){
        this.defaultDataSourceKey=key;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}

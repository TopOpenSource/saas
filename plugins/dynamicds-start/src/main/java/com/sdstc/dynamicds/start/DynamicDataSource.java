package com.sdstc.dynamicds.start;

import cn.hutool.Hutool;
import cn.hutool.core.math.MathUtil;
import cn.hutool.core.util.NumberUtil;
import com.sdstc.dynamicds.constant.DataSourceConstant;
import com.sdstc.dynamicds.dto.TenantDataSource;
import com.sdstc.dynamicds.service.DataSourceService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 动态数据源
 * @author cheng
 */
public class DynamicDataSource extends AbstractDataSource implements InitializingBean {

    private ApplicationContext applicationContext;

    public DynamicDataSource(ApplicationContext applicationContext){
        this.applicationContext=applicationContext;
    }

    @Nullable
    private Map<String, TenantDataSource> targetDataSources=new HashMap<>();

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
        String action=DBContextHolder.getReadOrWrite();

        if (key == null) {
           //默认数据源
            key = this.defaultDataSourceKey;
        }

        TenantDataSource tenantDataSource = this.targetDataSources.get(key);

        //如果数据源不存在重新初始化
        if (tenantDataSource == null){
            DataSourceService dataSourceService=this.applicationContext.getBean("dataSourceService", DataSourceService.class);
            dataSourceService.initDB(key);
            tenantDataSource = this.targetDataSources.get(key);
        }

        if (tenantDataSource != null) {
            //只读数据源
            if(DataSourceConstant.READ.equals(action)){
                int size=tenantDataSource.getReads().size();
                //随机选取数据源
                Random rand = new Random();
                return tenantDataSource.getReads().get(rand.nextInt(size)+1);
            }
            //读写数据源
            if(DataSourceConstant.WRITE.equals(action)){
                return tenantDataSource.getWrite();
            }

            throw new IllegalArgumentException("no datasource: " + key);
        } else {
            throw new IllegalArgumentException("no datasource: " + key);
        }
    }

    /**
     * 追加数据源
     * @param key
     * @param ds
     */
    public void putDataSource(String key,TenantDataSource ds){
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

    /**
     * 获取某一租户下的数据源
     * @param tenantId
     * @return
     */
    public TenantDataSource getTargetDataSources(String tenantId){
        TenantDataSource tenantDataSource = this.targetDataSources.get(tenantId);
        if(tenantDataSource==null){
            tenantDataSource=new TenantDataSource();
            this.targetDataSources.put(tenantId,tenantDataSource);
        }
        return tenantDataSource;
    }

    public static void main(String[] args) {
        Random rand = new Random();
       System.out.println(rand.nextInt(1)+1);
    }
}

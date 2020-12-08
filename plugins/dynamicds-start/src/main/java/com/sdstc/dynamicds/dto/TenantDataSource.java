package com.sdstc.dynamicds.dto;

import lombok.Data;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 租户与数据源关系
 */
@Data
public class TenantDataSource {
    //只读数据源
    private List<DataSource> reads=new ArrayList<>();
    //写数据源
    private  DataSource write;

}

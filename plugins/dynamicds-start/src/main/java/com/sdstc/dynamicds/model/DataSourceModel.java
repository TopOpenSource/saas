package com.sdstc.dynamicds.model;

import lombok.Data;

@Data
public class DataSourceModel {
    private Long id;
    private String dbConnect;
    private String dbUser;
    private String dbPwd;
    private String driverClassName;
    //是否可读
    private String read;
    //是否可写
    private String write;
    private Long tenantId;
}

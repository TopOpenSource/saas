package com.sdstc.dynamicds.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Tenant implements Serializable {
    private Long id;
    private String dbConnect;
    private String dbUser;
    private String dbPwd;
    private String driverClassName;

    private String name;
    private String state;
}

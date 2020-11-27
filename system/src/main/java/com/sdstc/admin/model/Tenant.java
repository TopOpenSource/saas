package com.sdstc.admin.model;

import lombok.Data;

@Data
public class Tenant {
    private Long id;
    private String dbConnect;
    private String dbUser;
    private String dbPwd;
    private String driverClassName;
}

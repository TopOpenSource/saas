package com.sdstc.pub.model;

import java.util.Date;

import lombok.Data;

/**
 * 
 * @author cheng
 *
 */
@Data
public class BaseModel {
    private Date gmtCreate;
    private Date gmtModified;
    private String createAccount;
    private String modifiedAccount;
    private String isDelete="0";
}

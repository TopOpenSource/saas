package com.sdstc.oauth2.integration;

import lombok.Data;

import java.util.Map;

/**
 * 认证信息类
 * @author cheng
 *
 */
@Data
public class IntegrationAuthentication {

	//用户名密码模式
	public final static String PWD="pwd";
	//短信验证码模式
	public final static String SMS="sms";
	
	//验证类型
    private String authType;
    //用户名
    private String username;
    //请求参数
    private Map<String,String[]> authParameters;

    public String getAuthParameter(String paramter){
        String[] values = this.authParameters.get(paramter);
        if(values != null && values.length > 0){
            return values[0];
        }
        return null;
    }
}

package com.sdstc.pub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultDto<T> {
	//通用 成功
	public final static Integer SUCCESS = 1;
	//通用 失败
	public final static Integer FAILE = -1;
	
	//获取token失败
	public final static Integer GET_TOKEN_FAILE=-1000;
	//刷新token失败
	public final static Integer REFRESH_TOKEN_FAILE=-1001;
	//无效的token
	public final static Integer INVALIDTOKEN=-1002;
	//无操作权限
    public final static Integer ACCESS_FAILE=-1003;
    //oauth2 验证失败
    public final static Integer OAUTH2_FAILE=-1004;
    public final static Integer FORBIDDEN_FAILE=-1005;
    public final static Integer METHODNOTALLOWED_FAILE=-1006;
    public final static Integer SERVERERROR_FAILE=-1007;
    
	//验证码验证失败
    public final static Integer VALIDCODE_FAILE=-2001;
    
    // 企业未实名认证
    public final static Integer CUSTOMER_REGISTER_FAILE=-1008;
    
    // 登陆手机验证码验证失败
    public final static Integer CHECK_LOGIN_VCCODE_FAILE=-1009;
    
	private Integer result;
	private String message;
	private T data;
	
	public ResultDto() {
		
	}
	
	public ResultDto(Integer result) {
		this.result = result;
	}
	
	public ResultDto(Integer result, String message) {
		this.result = result;
		this.message = message;
	}
}

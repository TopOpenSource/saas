package com.sdstc.pub.common;

import com.sdstc.pub.utils.JSONUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * 结果dto
 * @author cheng
 */
@Data
public class ResultDto implements Serializable {
	private Integer result;
	private String message;

	public ResultDto() {
		
	}
	
	public ResultDto(Integer result) {
		this.result = result;
	}
	
	public ResultDto(Integer result, String message) {
		this.result = result;
		this.message = message;
	}

	public ResultDto(ResultCode resultCode) {
		this.result = resultCode.getCode();
		this.message = resultCode.getMsg();
	}

	public String toJsonString(){
		return JSONUtil.parseObject(this);
	}



}

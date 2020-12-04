package com.sdstc.config;

import java.io.IOException;

import com.sdstc.pub.common.SystemCodeEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import com.sdstc.pub.common.ResultDto;

/**
 * 自定义异常处理，暂未使用
 * 
 * @author cheng
 *
 */
public class OauthExceptionTranslator implements WebResponseExceptionTranslator {
	private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

	@Override
	public ResponseEntity<ResultDto> translate(Exception e) throws Exception {
		
		Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);
		Exception ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, causeChain);

		ResultDto resultDto=new ResultDto(1,null);
		
		if (ase != null) {
			//resultDto.setResult(ResultDto.OAUTH2_FAILE);
			resultDto=new ResultDto(SystemCodeEnum.SYSTEM_NO_AUTH);
			return handleOAuth2Exception(resultDto);
		}

		ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);
		if (ase != null) {
			//resultDto.setResult(ResultDto.ACCESS_FAILE);
			resultDto=new ResultDto(SystemCodeEnum.SYSTEM_NO_AUTH);
			return handleOAuth2Exception(resultDto);
		}

		ase = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
		if (ase instanceof AccessDeniedException) {
			//resultDto.setResult(ResultDto.FORBIDDEN_FAILE);
			resultDto=new ResultDto(SystemCodeEnum.SYSTEM_NO_AUTH);
			return handleOAuth2Exception(resultDto);
		}

		ase = (HttpRequestMethodNotSupportedException) throwableAnalyzer.getFirstThrowableOfType(HttpRequestMethodNotSupportedException.class, causeChain);
		if (ase instanceof HttpRequestMethodNotSupportedException) {
			//resultDto.setResult(ResultDto.METHODNOTALLOWED_FAILE);
			resultDto=new ResultDto(SystemCodeEnum.SYSTEM_NO_AUTH);
			return handleOAuth2Exception(resultDto);
		}

		//resultDto.setResult(ResultDto.SERVERERROR_FAILE);
		resultDto=new ResultDto(SystemCodeEnum.SYSTEM_NO_AUTH);

		return handleOAuth2Exception(resultDto);

	}
	
	private ResponseEntity<ResultDto> handleOAuth2Exception(ResultDto resultDto) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Cache-Control", "no-store");
		headers.set("Pragma", "no-cache");
		
		ResponseEntity<ResultDto> responseEntity = new ResponseEntity<ResultDto>(resultDto, headers, HttpStatus.valueOf(200));
		return responseEntity;
	}

}

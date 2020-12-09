package com.sdstc.config.advice.requestEncode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdstc.config.advice.AdviceService;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * cheng
 */

@Service
public class DecodeEncodeRequetBody implements AdviceService {
    @Autowired
    private SM4Util sm4Util;

    @Override
    public Boolean isMate(MethodParameter parameter) {
        if (parameter.getMethod().isAnnotationPresent(SecretAnnotation.class)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * body 加密
     * @param inputMessage
     * @param parameter
     * @return
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter) {
        SecretAnnotation secretAnnotation = parameter.getMethodAnnotation(SecretAnnotation.class);
        if (secretAnnotation.decode()) {
            return new HttpInputMessage() {
                @Override
                public InputStream getBody() throws IOException {
                    String encodeBody = IOUtils.toString(inputMessage.getBody(), "utf-8");
                    return IOUtils.toInputStream(sm4Util.decodeStr(encodeBody), "utf-8");
                }

                @Override
                public HttpHeaders getHeaders() {
                    return inputMessage.getHeaders();
                }
            };
        }else{
            return inputMessage;
        }

    }

    @Override
    @SneakyThrows
    public Object beforeBodyWrite(Object body, MethodParameter parameter) {
        SecretAnnotation secretAnnotation = parameter.getMethodAnnotation(SecretAnnotation.class);
        if (secretAnnotation.encode()) {
            ObjectMapper objectMapper = new ObjectMapper();
            String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
            return sm4Util.encodeStr(result);
        } else {
            return body;
        }
    }
}

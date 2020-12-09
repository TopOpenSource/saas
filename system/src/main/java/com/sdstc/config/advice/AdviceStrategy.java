package com.sdstc.config.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * cheng
 */

@Service
public class AdviceStrategy{

    private List<AdviceService> allAdviceServices = new ArrayList<>();

    public AdviceStrategy(List<AdviceService> adviceServices) {
        allAdviceServices.addAll(adviceServices);
    }

    /**
     * 执行beforeBodyRead  支持链式操作，不支持排序
     * @param inputMessage
     * @param parameter
     * @return
     */
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter){
        for(AdviceService adviceService:allAdviceServices){
            if(adviceService.isMate(parameter)){
                inputMessage=adviceService.beforeBodyRead(inputMessage,parameter);
            }
        }
        return inputMessage;
    }

    /**
     * 执行 beforeBodyWrite 支持链式操作，不支持排序
     * @param body
     * @param parameter
     * @return
     */
    public Object beforeBodyWrite(Object body,MethodParameter parameter){
        for(AdviceService adviceService:allAdviceServices){
            if(adviceService.isMate(parameter)){
                body=adviceService.beforeBodyWrite(body,parameter);
            }
        }
        return body;
    }
}

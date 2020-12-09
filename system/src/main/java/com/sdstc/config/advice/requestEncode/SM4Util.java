package com.sdstc.config.advice.requestEncode;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * cheng
 */

@Component
public class SM4Util {
    /**
     * 密钥
     */
    @Value("${httpEncodeDecodeKey}")
    private String key;

    /**
     * 加密
     * @param content
     * @return
     */
    public  String encodeStr(String content){
        SM4 sm4 = (SM4) SmUtil.sm4(key.getBytes());
        return sm4.encryptHex(content);
    }

    /**
     * 解密
     * @param content
     * @return
     */
    public  String decodeStr(String content) {
        SM4 sm4 = (SM4) SmUtil.sm4(key.getBytes());
        return sm4.decryptStr(content);
    }

}

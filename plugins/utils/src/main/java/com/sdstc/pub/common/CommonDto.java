package com.sdstc.pub.common;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.BeanCopier;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

/**
 * @author cheng
 * @date 2020/10/12
 */
public class CommonDto extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * build
     * 
     * @return
     */
    public static CommonDto build() {
        return new CommonDto();
    }

    /**
     * build 带默认值
     * 
     * @param key
     * @param value
     * @return
     */
    public static CommonDto build(String key, String value) {
        return CommonDto.build().put(key, value);
    }

    /**
             * 设置key value 并返回 this 用于 .put("a", "b").put("a", "b").put("a", "b");
     */
    @Override
    public CommonDto put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**************** dto get各种类型的值 **************/

    public String getAsString(String key) {
        return MapUtil.getStr(this, key);
    }

    public Integer getAsInteger(String key) {
        return MapUtil.getInt(this, key);
    }

    public Long getAsLong(String key) {
        return MapUtil.getLong(this, key);
    }

    public BigDecimal getAsBigDecimal(String key) {
        return MapUtil.get(this, key, BigDecimal.class);
    }
    
    public <T> T getAsObject(String key,Class<T> clazz) {
        return MapUtil.get(this, key, clazz);
    }
    
    /**
     * dto 转类
     * 
     * @param <T>
     * @param clazz
     * @return
     */
    public <T> T covert2Class(Class<T> clazz) {
        return BeanUtil.toBean(this, clazz);
    }

    /**
     * class 转dto
     * 
     * @param obj
     * @return
     */
    public static CommonDto convertClass2Dto(Object obj) {
        return BeanCopier.create(obj, new CommonDto(),CopyOptions.create().setIgnoreNullValue(false).setFieldNameEditor(key->key)).copy();
    }

    /**
     * dto 转json
     * 
     * @return
     */
    public String covert2Json() {
        return  JSONUtil.toJsonStr(this);
    }

    /**
     * 
     * @param jsonStr
     * @return
     */
    public static CommonDto convertJson2Dto(String jsonStr) {
        return JSONUtil.toBean(jsonStr, CommonDto.class);
    }

}

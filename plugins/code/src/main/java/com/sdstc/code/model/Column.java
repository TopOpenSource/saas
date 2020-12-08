package com.sdstc.code.model;

import lombok.Data;

@Data
public class Column {
   private String columnName;
   private String dataType;
   private String columnKey;
   //备注
   private String columnComment;
   //Java属性名
   private String javaColumnName;
   //Java类型
   private String javaDataType;
   //是否主键
   private Boolean isPK;
   //是否继承属性
   private Boolean isParentCol;
   
}

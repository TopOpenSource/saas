package com.sdstc.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sdstc.code.model.Column;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface TableMapper {
	@Select("select  t.column_name,t.data_type,t.column_key,t.column_comment " +
			"from information_schema.columns  t " +
			"where t.TABLE_NAME=#{tableName} and t.TABLE_SCHEMA=#{scheme} " +
			"order by t.ORDINAL_POSITION")

	@Results(id = "ColumnMap",value = {
			@Result(property = "id",column = "id"),
			@Result(property = "columnName",column = "column_name"),
			@Result(property = "dataType",column = "data_type"),
			@Result(property = "columnKey",column = "column_key"),
			@Result(property = "columnComment",column = "column_comment"),
	})
	List<Column> getColumns(@Param("tableName")String tableName,@Param("scheme")String scheme);
}

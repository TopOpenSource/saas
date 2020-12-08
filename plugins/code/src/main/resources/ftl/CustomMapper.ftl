<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${customDaoPackage}.${entityName}Dao">
	
	<resultMap id="${entityName}Map" type="${modelPackage}.${entityName}">
	<#list cols as col>
	  <#if col.isPK?string("true","false")== "true">	
	    <id column="${col.columnName}" property="${col.javaColumnName}" />
	  <#else>
	    <result column="${col.columnName}" property="${col.javaColumnName}" />
	  </#if>
	</#list>
	</resultMap>
	
    <sql id="${entityName}Cols">
      <#list cols as col>${col.columnName}<#if col_has_next>,</#if></#list>
    </sql>
    
    <sql id="${entityName}Props">
      <#list cols as col>${"#{"+col.javaColumnName+"}"}<#if col_has_next>,</#if></#list>
    </sql>
    
    <sql id="${entityName}Where">
      
    </sql>
    
    <sql id="${entityName}DtoWhere">
      
    </sql>
	
	<select id="selectByDto"  parameterType="${modelPackage}.${entityName}" resultMap="${entityName}Map">
		select
			<include refid="${entityName}Cols"></include>
		from ${tableName}
		<where>
		    <include refid="${entityName}Where"></include>
		</where>
	</select>
	
	<select id="selectCountByDto"  parameterType="${modelPackage}.${entityName}" resultType="Integer">
		select
			count(*)
		from ${tableName}
		<where>
		    <include refid="${entityName}Where"></include>
		</where>
	</select>
	
	<select id="selectPageByDto" resultMap="${entityName}Map">
		select
			<include refid="${entityName}Cols"></include>
		from ${tableName}
		<where>
		    <include refid="${entityName}DtoWhere"></include>
		</where>
		limit ${r"#{pageDto.start}"},${r"#{pageDto.pageSize}"}
	</select>
</mapper>
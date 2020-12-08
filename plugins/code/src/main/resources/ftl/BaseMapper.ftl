<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${baseDaoPackage}.${entityName}BaseDao">
	
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
    
    <insert id="insert" parameterType="${modelPackage}.${entityName}">
        insert into ${tableName}
        (<include refid="${entityName}Cols"></include>)
        values
        (<include refid="${entityName}Props"></include>)
    </insert>
        
    <update id="updateSelectiveByPK" parameterType="${modelPackage}.${entityName}">
		update ${tableName}
		<set>
		<#-- 遍历属性-->
		<#list cols as col>
		 <#-- 属性不是主键-->
		 <#if col.javaColumnName != primaryKey>
		 <#-- 属性不是租户键-->
		  <#if tenantKey?? || col.javaColumnName != tenantKey!>
		  
		  <#-- 属性为字符串类型-->
	      <#if col.javaDataType=="String">
	        <if test="${col.javaColumnName} != null and ${col.javaColumnName} != ''">
		        ${col.columnName} = ${"#{"+col.javaColumnName+"}"},
		    </if>
	      <#else>
	        <if test="${col.javaColumnName} != null">
		        ${col.columnName} = ${"#{"+col.javaColumnName+"}"},
		    </if>
	      </#if>
	      </#if> 
	     </#if> 
		</#list>    
		</set>
		<where>
		  ${sqlPk} = ${"#{"+primaryKey+"}"} 
		  <#if hasTenant?string("true","false")== "true">
		  and ${sqlTenantKey} = ${"#{"+tenantKey+"}"}  
		  </#if>
		</where> 
	</update>
	
	<update id="updateByPK" parameterType="${modelPackage}.${entityName}">
		update ${tableName}
		<set>
	<#list cols as col>
    <#-- 属性不是主键-->
	 <#if col.javaColumnName != primaryKey>
	 <#-- 属性不是租户键-->
	  <#if tenantKey?? || col.javaColumnName != tenantKey!>
	    ${col.columnName} = ${"#{"+col.javaColumnName+"}"},
	  </#if>
	 </#if>  
	</#list>	  
		</set>
		<where>
		  ${sqlPk} = ${"#{"+primaryKey+"}"} 
		  <#if hasTenant?string("true","false")== "true">
		  and ${sqlTenantKey} = ${"#{"+tenantKey+"}"} 
		  </#if>
		</where> 
	</update>
	
	<delete id="deleteByPK">
	   <!--存在删除状态键则逻辑删除-->
	    <#if hasDel?string("true","false")== "false">
		delete from ${tableName}
		<where>
		  ${sqlPk} = ${"#{"+primaryKey+"}"} 
		  <#if hasTenant?string("true","false")== "true">
		  and ${sqlTenantKey} = ${"#{"+tenantKey+"}"}  
		  </#if>
		</where> 
		<#else>
		update ${tableName}
		set ${sqlDelKey} = '1'
		<where>
		  ${sqlPk} = ${"#{"+primaryKey+"}"} 
		  <#if hasTenant?string("true","false")== "true">
		  and ${sqlTenantKey} = ${"#{"+tenantKey+"}"}  
		  </#if>
		</where> 
		</#if>
	</delete>
	
	<select id="selectByPK" resultMap="${entityName}Map">
		select
			<include refid="${entityName}Cols"></include>
		from ${tableName}
		<where>
		  ${sqlPk} = ${"#{"+primaryKey+"}"} 
		  <#if hasTenant?string("true","false")== "true">
		  and ${sqlTenantKey} = ${"#{"+tenantKey+"}"}  
		  </#if>
		  <#if hasDel?string("true","false")== "true">
		  and ${sqlDelKey} = '0'
		  </#if>
		</where> 
	</select>
	
</mapper>
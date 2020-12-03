package com.sdstc.oauth2.dao;

import java.util.List;

import com.sdstc.dynamicds.model.Tenant;
import org.apache.ibatis.annotations.Param;

import com.sdstc.oauth2.model.Perm;
import com.sdstc.oauth2.model.Role;
import com.sdstc.oauth2.model.UserInfo;

/**
 * 
 * @author cheng
 *
 */
public interface UserDao {
   UserInfo getUser(@Param("account")String account);
   List<Role> getRolesByUser(@Param("account")String account);
   List<Perm> getPermsByUser(@Param("account")String account);
   List<Tenant> getTenantsByUserAccount(@Param("account")String account);
}

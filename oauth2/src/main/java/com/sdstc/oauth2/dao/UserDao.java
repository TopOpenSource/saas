package com.sdstc.oauth2.dao;

import com.sdstc.oauth2.model.Tenant;
import com.sdstc.oauth2.model.Perm;
import com.sdstc.oauth2.model.Role;
import com.sdstc.oauth2.model.Url;
import com.sdstc.oauth2.model.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author cheng
 *
 */
public interface UserDao {
   /**
    * 获取用户的信息
    * @param account
    * @return
    */
   UserInfo getUser(@Param("account")String account);

   /**
    * 获取用户的角色
    * @param userId
    * @return
    */
   List<Role> getRolesByUser(@Param("userId")Long userId);

   /**
    * 获取用户的权限
    * @param userId
    * @return
    */
   List<Perm> getPermsByUser(@Param("userId")Long userId);

   /**
    * 获取用户的url权限
    * @param userId
    * @return
    */
   List<Url> getUrlsByUser(@Param("userId")Long userId);

   /**
    * 获取用户的租户
    * @param userId
    * @return
    */
   List<Tenant> getTenantsByUserId(@Param("userId")Long userId);
}

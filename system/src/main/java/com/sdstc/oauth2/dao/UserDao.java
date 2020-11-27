package com.sdstc.oauth2.dao;

import com.sdstc.oauth2.model.Customer;
import com.sdstc.oauth2.model.Perm;
import com.sdstc.oauth2.model.Role;
import com.sdstc.oauth2.model.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author cheng
 *
 */
public interface UserDao {
   UserInfo getUser(@Param("account")String account);
   List<Role> getRolesByUser(@Param("account")String account,@Param("customerId")Long customerId);
   List<Perm> getPermsByUser(@Param("account")String account,@Param("customerId")Long customerId);
   List<Customer> getCustomersByUser(UserInfo user);
}

package com.sdstc.oauth2.dao;

import org.apache.ibatis.annotations.Param;
import com.sdstc.oauth2.model.Client;

/**
 * 
 * @author cheng
 *
 */
public interface ClientDao {
  Client getClientById(@Param("clientId")String clientId);
}

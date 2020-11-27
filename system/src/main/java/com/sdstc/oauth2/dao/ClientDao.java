package com.sdstc.oauth2.dao;

import com.sdstc.oauth2.model.Client;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author cheng
 *
 */
public interface ClientDao {
  Client getClientById(@Param("clientId")String clientId);
}

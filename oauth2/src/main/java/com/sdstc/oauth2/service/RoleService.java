package com.sdstc.oauth2.service;

import com.sdstc.oauth2.model.Perm;
import com.sdstc.oauth2.model.Role;

import java.util.List;

public interface RoleService {
    /**
     * 获取角色
     *
     * @param account
     * @param account
     * @return
     */
    List<Role> getRolesByUser(String account,String tenantId);

    /**
     * 获取权限
     *
     * @param account
     * @return
     */
     List<Perm> getPermsByUser(String account,String tenantId);

    /**
     * 验证是否对URL有权限
     * @param account
     * @param tenantId
     * @return
     */
     Boolean hasPerm(String account,String url,String tenantId);
}

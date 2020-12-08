package com.sdstc.oauth2.service;

import com.sdstc.oauth2.model.Perm;
import com.sdstc.oauth2.model.Role;

import java.util.List;

public interface RoleService {
    /**
     * 获取角色
     *
     * @param userId
     * @param tenantId
     * @return
     */
    List<Role> getRolesByUser(Long userId,String tenantId);

    /**
     * 获取权限
     *
     * @param userId
     * @param tenantId
     * @return
     */
     List<Perm> getPermsByUser(Long userId,String tenantId);

    /**
     * 验证是否对URL有权限
     * @param userId
     * @param tenantId
     * @return
     */
     Boolean hasPerm(Long userId,String url,String tenantId);
}

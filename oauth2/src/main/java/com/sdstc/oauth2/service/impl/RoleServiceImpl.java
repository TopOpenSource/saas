package com.sdstc.oauth2.service.impl;

import com.sdstc.dynamicds.config.DBContextHolder;
import com.sdstc.dynamicds.constant.TenantConstant;
import com.sdstc.oauth2.dao.UserDao;
import com.sdstc.oauth2.model.Perm;
import com.sdstc.oauth2.model.Role;
import com.sdstc.oauth2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<Role> getRolesByUser(String account,String tenantId) {
        DBContextHolder.setDbKey(tenantId);
        List<Role> roles = userDao.getRolesByUser(account);
        DBContextHolder.setDbKey(TenantConstant.defaultDBKey);
        return roles;
    }

    @Override
    public List<Perm> getPermsByUser(String account,String tenantId) {
        DBContextHolder.setDbKey(tenantId);
        List<Perm> perms = userDao.getPermsByUser(account);
        DBContextHolder.setDbKey(TenantConstant.defaultDBKey);
        return  perms;
    }
}

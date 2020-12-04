package com.sdstc.oauth2.service.impl;

import com.sdstc.dynamicds.config.DBContextHolder;
import com.sdstc.dynamicds.constant.TenantConstant;
import com.sdstc.oauth2.dao.UserDao;
import com.sdstc.oauth2.model.Perm;
import com.sdstc.oauth2.model.Role;
import com.sdstc.oauth2.model.Url;
import com.sdstc.oauth2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public Boolean hasPerm(String account,String url,String tenantId) {
        DBContextHolder.setDbKey(tenantId);
        boolean hasPerm=false;
        //规则验证
        PathMatcher matcher = new AntPathMatcher();
        /**
         * 获取url并于请求的url进行比对
         */
        List<Url> urls=userDao.getUrlsByUser(account);

        List<Url> matchUrl=urls.stream().filter(x -> {
            return matcher.match(x.getUrl(),url);
        }).collect(Collectors.toList());

        if(matchUrl!=null&&matchUrl.size()>0){
            hasPerm=true;
        }

        DBContextHolder.setDbKey(TenantConstant.defaultDBKey);
        return hasPerm;
    }
}

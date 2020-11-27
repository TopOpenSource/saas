package com.sdstc.oauth2.service.impl;

import com.sdstc.oauth2.dao.UserDao;
import com.sdstc.oauth2.integration.IntegrationAuthentication;
import com.sdstc.oauth2.model.*;
import com.sdstc.oauth2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisService redisService;

    /**
     * 获取角色
     *
     * @param account
     * @param customerId
     * @return
     */
    private List<Role> getRolesByUser(String account, Long customerId) {
        return userDao.getRolesByUser(account, customerId);
    }

    /**
     * 获取权限
     *
     * @param account
     * @param customerId
     * @return
     */
    private List<Perm> getPermsByUser(String account, Long customerId) {
        return userDao.getPermsByUser(account, customerId);
    }

    @Override
    public List<Customer> getCustomersByUser(UserInfo user) {
        return userDao.getCustomersByUser(user);
    }

    @Override
    public UserInfo getUser(String account) {
        UserInfo userInfo = userDao.getUser(account);
        return userInfo;
    }

    @Override
    public UserSecurity getUserSecurity(String account, Long customerId, String authType) {
        // 获取用户信息
        UserInfo userInfo = this.getUser(account);

        account = userInfo.getAccount();

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        // 获取所属的全部客户信息
        List<Customer> customers = this.getCustomersByUser(new UserInfo(account, null));

        Customer customer = null;

        if (customers != null && customers.size() > 0) {
            //存在租户
            if (customerId == null) {
                // 默认取第一个
                customer = customers.get(0);
            } else {
                //验证传入的租户是否与实际相符
                List<Customer> customers2 = customers.stream().filter(x -> {
                    return x.getId().equals(customerId);
                }).collect(Collectors.toList());

                if (customers2 != null && customers2.size() == 1) {
                    customer = customers2.get(0);
                } else {
                    log.error("no cusotemer!");
                    return null;
                }
            }


            // 获取角色
            List<Role> roles = this.getRolesByUser(account, customer.getId());
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getCode()));

            }
            // 获取权限
            List<Perm> perms = this.getPermsByUser(account, customer.getId());
            for (Perm perm : perms) {
                authorities.add(new SimpleGrantedAuthority(perm.getCode()));
            }


        } else {
            //不存在租户
            authorities.add(new SimpleGrantedAuthority("NO_CUSTOMER"));
        }
        // 生成 springsecurity User
        UserSecurity user = null;
        if (authType == null) {
            authType = IntegrationAuthentication.PWD;
        }

        if (IntegrationAuthentication.PWD.equals(authType)) {
            user = new UserSecurity(account, userInfo.getPwd(), authorities);
        } else if (IntegrationAuthentication.SMS.equals(authType)) {
            user = new UserSecurity(account, userInfo.getSmsCode(), authorities);
        } else {
            log.error("不支持此验证类型" + authType);
        }
        user.setCustomer(customer);
        user.setCustomers(customers);
        user.setUserName(userInfo.getName());
        user.setEmail(userInfo.getEmail());
        user.setPhone(userInfo.getPhone());
        return user;
    }
}

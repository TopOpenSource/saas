package com.sdstc.oauth2.service.impl;

import com.sdstc.dynamicds.start.DBContextHolder;
import com.sdstc.dynamicds.constant.TenantConstant;
import com.sdstc.dynamicds.model.Tenant;
import com.sdstc.oauth2.dao.UserDao;
import com.sdstc.oauth2.integration.IntegrationAuthentication;
import com.sdstc.oauth2.model.*;
import com.sdstc.oauth2.service.RoleService;
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
	@Autowired
	private RoleService roleService;



	@Override
	public List<Tenant> getTenantsByUserAccount(String account) {
		return userDao.getTenantsByUserAccount(account);
	}

	@Override
	public UserInfo getUser(String account) {
		UserInfo userInfo=userDao.getUser(account);
		return userInfo;
	}

	/**
	 * 获取用户信息
	 * @param account
	 * @param tenantId defaultID
	 * @param authType  验证类型
	 * @return
	 */
	@Override
	public UserSecurity getUserSecurity(String account, Long tenantId, String authType) {
		DBContextHolder.setDbKey(TenantConstant.defaultDBKey);
		// 获取用户信息
		UserInfo userInfo = this.getUser(account);
		account  = userInfo.getAccount();

		List<GrantedAuthority> authorities = new ArrayList<>();

		// 获取所属的租户
		List<Tenant> tenants = this.getTenantsByUserAccount(account);

		Tenant tenant = null;

		if(tenants!=null && tenants.size()>0){
			//存在租户
			if (tenantId == null) {
				// 默认取第一个
				tenant = tenants.get(0);
			} else {
				//验证传入的租户是否与实际相符
				Long finalTenantId = tenantId;
				List<Tenant> tenants2 = tenants.stream().filter(x -> {
					return x.getId().equals(finalTenantId);
				}).collect(Collectors.toList());

				if (tenants2 != null && tenants2.size() == 1) {
					tenant = tenants2.get(0);
				} else {
					log.error("no cusotemer!");
					return null;
				}
			}
			tenantId=tenant.getId();
			// 获取角色
			List<Role> roles = roleService.getRolesByUser(account,String.valueOf(tenantId));
			for (Role role : roles) {
				authorities.add(new SimpleGrantedAuthority(role.getCode()));

			}
			// 获取权限
			List<Perm> perms = roleService.getPermsByUser(account,String.valueOf(tenantId));
			for (Perm perm : perms) {
				authorities.add(new SimpleGrantedAuthority(perm.getCode()));
			}

		}else{
			//不存在租户
			authorities.add(new SimpleGrantedAuthority("NO_CUSTOMER"));
		}

		// 生成 springsecurity User
		UserSecurity user=null;
		if(authType==null) {
			authType=IntegrationAuthentication.PWD;
		}
		
		if(IntegrationAuthentication.PWD.equals(authType)) {
			user = new UserSecurity(account, userInfo.getPwd(), authorities);
		}else if(IntegrationAuthentication.SMS.equals(authType)) {
			user = new UserSecurity(account, userInfo.getSmsCode(), authorities);
		}else {
			log.error("不支持此验证类型"+authType);
		}

		user.setTenant(tenant);
		user.setTenants(tenants);
		user.setUserName(userInfo.getName());
		user.setEmail(userInfo.getEmail());
		user.setPhone(userInfo.getPhone());
		return user;
	}
}

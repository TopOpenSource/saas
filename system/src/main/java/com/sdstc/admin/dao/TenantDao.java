package com.sdstc.admin.dao;

import com.sdstc.admin.model.Tenant;

import java.util.List;

public interface TenantDao {
    List<Tenant> selTenants();
}

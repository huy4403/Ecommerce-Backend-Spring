package com.website.ecommerce.service.admin;

import com.website.ecommerce.model.Role;

import java.util.List;

public interface AdminRoleService {
    Role findByName(String name);
    List<Role> getAllRoles();
    Role save(Role role);
    Role update(Role role);
    void delete(int id);
    Role getRoleById(int id);
}

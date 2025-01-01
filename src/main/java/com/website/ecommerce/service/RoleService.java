package com.website.ecommerce.service;

import com.website.ecommerce.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String name);
}

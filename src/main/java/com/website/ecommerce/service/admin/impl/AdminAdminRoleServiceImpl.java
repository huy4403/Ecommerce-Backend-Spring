package com.website.ecommerce.service.admin.impl;

import com.website.ecommerce.exception.HandleException;
import com.website.ecommerce.model.Role;
import com.website.ecommerce.repository.RoleRepository;
import com.website.ecommerce.service.admin.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AdminAdminRoleServiceImpl implements AdminRoleService {
    @Autowired
    public RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if(!role.isPresent()){
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", "Không tìm thấy quyền này");
            throw new HandleException(errors);
        }
        return role.get();
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    @Override
    public Role save(Role role) {
        if(roleRepository.existsByName(role.getName())) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", "Quyền này đã tồn tại");
            throw new HandleException(errors);
        }
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        Role existRole = findByName(role.getName());
        if(roleRepository.existRoleByNameDiffId(existRole.getName(), existRole.getId())) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", "Quyền này đã tồn tại");
            throw new HandleException(errors);
        }
        existRole.setName(role.getName());
        return roleRepository.save(existRole);
    }

    @Override
    public void delete(int id) {
        Optional<Role> role = roleRepository.findById(id);
        if(!role.isPresent()){
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", "Không tìm thấy quyền này");
            throw new HandleException(errors);
        }
        roleRepository.deleteById(id);
    }

    @Override
    public Role getRoleById(int id) {
        Optional<Role> role = roleRepository.findById(id);
        if(!role.isPresent()){
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", "Không tìm thấy quyền này");
            throw new HandleException(errors);
        }
        return role.get();
    }
}

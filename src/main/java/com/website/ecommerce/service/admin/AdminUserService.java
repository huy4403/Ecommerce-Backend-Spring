package com.website.ecommerce.service.admin;

import com.website.ecommerce.dtos.adminDTOs.AdminCreateUserDTO;
import com.website.ecommerce.dtos.adminDTOs.AdminUpdateUserDTO;
import com.website.ecommerce.model.User;

import java.util.List;

public interface AdminUserService {
    User save(AdminCreateUserDTO adminCreateUserDTO);
    User update(AdminUpdateUserDTO adminUpdateUserDTO);
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUserById(Long id);
    Boolean existsByUsername(String username);
    Boolean existsByPhone(String phone);
    Boolean existsByEmail(String email);
}

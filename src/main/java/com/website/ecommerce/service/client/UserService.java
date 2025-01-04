package com.website.ecommerce.service.client;

import com.website.ecommerce.dtos.userDTOs.UserChangePasswordDTO;
import com.website.ecommerce.dtos.userDTOs.UserUpdateDTO;
import com.website.ecommerce.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    Boolean existsByPhoneDiffUserId(String phone, Long id);
    Boolean existsByEmailDiffUserId(String email, Long id);
    User update(UserUpdateDTO userUpdateDTO);
    User getUserById(Long id);
    User changePassword(UserChangePasswordDTO userChangePasswordDTO);
}

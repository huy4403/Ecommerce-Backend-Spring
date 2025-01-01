package com.website.ecommerce.service;

import com.website.ecommerce.dtos.userDTOs.UserLoginDTO;
import com.website.ecommerce.dtos.userDTOs.UserRegistrationDTO;
import com.website.ecommerce.model.User;
import com.website.ecommerce.response.UserLoginResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Boolean existsByUsername(String username);
    User findByUsername(String username);
    Boolean existsByPhone(String phone);
    Boolean existsByEmail(String email);
    User save(UserRegistrationDTO userRegistrationDTO);

    UserLoginResponse login(UserLoginDTO userLoginDTO);

}

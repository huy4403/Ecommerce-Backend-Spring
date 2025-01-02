package com.website.ecommerce.service.auth;

import com.website.ecommerce.dtos.authDTOs.AuthLoginDTO;
import com.website.ecommerce.dtos.authDTOs.AuthRegistrationDTO;
import com.website.ecommerce.model.User;
import com.website.ecommerce.response.UserLoginResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface AuthService extends UserDetailsService {
    Boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    Boolean existsByPhone(String phone);
    Boolean existsByEmail(String email);
    User save(AuthRegistrationDTO authRegistrationDTO);

    UserLoginResponse login(AuthLoginDTO authLoginDTO);
}

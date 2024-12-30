package com.website.ecommerce.service;

import com.website.ecommerce.dtos.userDTOs.UserLoginDTO;
import com.website.ecommerce.dtos.userDTOs.UserRegistrationDTO;
import com.website.ecommerce.model.User;

public interface UserService {

    User register(UserRegistrationDTO userDto);

    User login(UserLoginDTO userDto);

}

package com.website.ecommerce.service.impl;

import com.website.ecommerce.dtos.userDTOs.UserLoginDTO;
import com.website.ecommerce.dtos.userDTOs.UserRegistrationDTO;
import com.website.ecommerce.exception.ValidateException;
import com.website.ecommerce.model.Cart;
import com.website.ecommerce.model.User;
import com.website.ecommerce.repository.UserRepository;
import com.website.ecommerce.service.CartService;
import com.website.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartService cartService;

    @Override
    public User register(UserRegistrationDTO userDto) {

        User user = new User();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setGender(userDto.getGender());
        user.setBirthday(userDto.getBirthday());

        HashMap<String, String> errors = new HashMap<>();
        if(userRepository.existsByUsername(user.getUsername())) {
            errors.put("username", "Tên tài khoản đã tồn tại!");
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            errors.put("email", "Email đã được sử dụng để đăng ký tài khoản khác!");
        }
        if(userRepository.existsByPhone(user.getPhone())) {
            errors.put("phone", "đã được sử dụng để đăng ký tài khoản khác");
        }
        if(!errors.isEmpty()){
            throw new ValidateException(errors);
        }
        User registerUser = userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(user);
        cartService.createCart(cart);
        return registerUser;
    }

    @Override
    public User login(UserLoginDTO userDto) {
        User loginUser = userRepository.findUserByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        if(loginUser == null){
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", "Tài khoản hoặc mật khẩu không chính xác");
            throw new ValidateException(errors);
        }
        return loginUser;
    }
}

package com.website.ecommerce.service.admin.impl;

import com.website.ecommerce.dtos.adminDTOs.AdminCreateUserDTO;
import com.website.ecommerce.dtos.adminDTOs.AdminUpdateUserDTO;
import com.website.ecommerce.exception.HandleException;
import com.website.ecommerce.model.Cart;
import com.website.ecommerce.model.User;
import com.website.ecommerce.repository.UserRepository;
import com.website.ecommerce.service.admin.AdminUserService;
import com.website.ecommerce.service.client.CartService;
import com.website.ecommerce.service.admin.AdminRoleService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private CartService cartService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminUserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(AdminCreateUserDTO adminCreateUserDTO) {
        HashMap<String, String> errors = new HashMap<>();
        if(existsByUsername(adminCreateUserDTO.getUsername())) {
            errors.put("username", "Tên tài khoản đã tồn tại!");
        }
        if(existsByEmail(adminCreateUserDTO.getEmail())) {
            errors.put("email", "Email đã được sử dụng để đăng ký tài khoản khác!");
        }
        if(existsByPhone(adminCreateUserDTO.getPhone())) {
            errors.put("phone", "đã được sử dụng để đăng ký tài khoản khác");
        }
        if(!errors.isEmpty()){
            throw new HandleException(errors);
        }
        User user = User.builder()
                .name(adminCreateUserDTO.getName())
                .username(adminCreateUserDTO.getUsername())
                .password(passwordEncoder.encode(adminCreateUserDTO.getPassword()))
                .phone(adminCreateUserDTO.getPhone())
                .email(adminCreateUserDTO.getEmail())
                .gender(adminCreateUserDTO.getGender())
                .birthday(adminCreateUserDTO.getBirthday())
                .role(adminCreateUserDTO.getRole())
                .build();

        User registerUser = userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(user);
        cartService.createCart(cart);
        return registerUser;
    }

    @Override
    public User update(AdminUpdateUserDTO adminUpdateUserDTO) {
        User existUser = getUserById(adminUpdateUserDTO.getId());
        HashMap<String, String> errors = new HashMap<>();
        if(userRepository.existUserByUsernameDiffUserId(adminUpdateUserDTO.getUsername(), existUser.getId())) {
            errors.put("username", "Tài khoản đã được sử dụng để để đăng ký với người dùng khác");
        }
        if(userRepository.existUserByPhoneDiffUserId(adminUpdateUserDTO.getPhone(), existUser.getId())) {
            errors.put("phone", "Số điện thoại đã được sử dụng để để đăng ký với người dùng khác");
        }
        if(userRepository.existUserByEmailDiffUserId(adminUpdateUserDTO.getEmail(), existUser.getId())) {
            errors.put("email", "Email đã được sử dụng để để đăng ký với người dùng khác");
        }
        if(!errors.isEmpty()) {
            errors.put("message","Cập nhật người dùng thất bại");
            throw new HandleException(errors);
        }
        existUser.setName(adminUpdateUserDTO.getName());
        existUser.setUsername(adminUpdateUserDTO.getUsername());
        existUser.setPassword(adminUpdateUserDTO.getPassword());
        existUser.setPhone(adminUpdateUserDTO.getPhone());
        existUser.setEmail(adminUpdateUserDTO.getEmail());
        existUser.setGender(adminUpdateUserDTO.getGender());
        existUser.setBirthday(adminUpdateUserDTO.getBirthday());
        existUser.setRole(adminUpdateUserDTO.getRole());

        return userRepository.save(existUser);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", "Không tìm thấy người dùng nào");
            throw new HandleException(errors);
        }
        return users;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", "Không tìm thấy người dùng");
            throw new HandleException(errors);
        }
        return user.get();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}

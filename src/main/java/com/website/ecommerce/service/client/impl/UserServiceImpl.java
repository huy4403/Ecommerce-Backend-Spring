package com.website.ecommerce.service.client.impl;

import com.website.ecommerce.component.JwtProvider;
import com.website.ecommerce.dtos.authDTOs.AuthRegistrationDTO;
import com.website.ecommerce.dtos.userDTOs.UserUpdateDTO;
import com.website.ecommerce.exception.HandleException;
import com.website.ecommerce.model.Cart;
import com.website.ecommerce.model.Role;
import com.website.ecommerce.model.User;
import com.website.ecommerce.repository.UserRepository;
import com.website.ecommerce.service.client.CartService;
import com.website.ecommerce.service.admin.AdminRoleService;
import com.website.ecommerce.service.client.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User update(UserUpdateDTO userUpdateDTO) {
        HashMap<String, String> errors = new HashMap<>();
        User existUser = userRepository.findById(userUpdateDTO.getId()).get();
        if(existUser != null) {
            errors.put("message", "người dùng không tồn tại");
            throw new HandleException(errors);
        }
        if(existsByEmailDiffUserId(userUpdateDTO.getEmail(), userUpdateDTO.getId())) {
            errors.put("email", "Email đã được sử dụng để đăng ký tài khoản khác!");
        }
        if(existsByPhoneDiffUserId(userUpdateDTO.getPhone(), userUpdateDTO.getId())) {
            errors.put("phone", "đã được sử dụng để đăng ký tài khoản khác");
        }
        if(!errors.isEmpty()){
            throw new HandleException(errors);
        }
        existUser.setName(userUpdateDTO.getName());
        existUser.setEmail(userUpdateDTO.getEmail());
        existUser.setPhone(userUpdateDTO.getPhone());
        existUser.setGender(userUpdateDTO.getGender());
        existUser.setBirthday(userUpdateDTO.getBirthday());

        User updateUser = userRepository.save(existUser);
        return updateUser;
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

    //Security
    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found -> username or password " + username));
        return user;
    }

    @Override
    public Boolean existsByPhoneDiffUserId(String phone, Long id) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public Boolean existsByEmailDiffUserId(String email, Long id) {
        return userRepository.existsByEmail(email);
    }
}

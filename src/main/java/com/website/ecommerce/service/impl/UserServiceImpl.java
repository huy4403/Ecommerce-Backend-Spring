package com.website.ecommerce.service.impl;

import com.website.ecommerce.component.JwtProvider;
import com.website.ecommerce.dtos.userDTOs.UserLoginDTO;
import com.website.ecommerce.dtos.userDTOs.UserRegistrationDTO;
import com.website.ecommerce.exception.ValidateException;
import com.website.ecommerce.model.Cart;
import com.website.ecommerce.model.Role;
import com.website.ecommerce.model.User;
import com.website.ecommerce.repository.UserRepository;
import com.website.ecommerce.response.UserLoginResponse;
import com.website.ecommerce.service.CartService;
import com.website.ecommerce.service.RoleService;
import com.website.ecommerce.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private CartService cartService;
    @Autowired
    private RoleService roleService;

    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User save(UserRegistrationDTO userDto) {
        HashMap<String, String> errors = new HashMap<>();
        if(userRepository.existsByUsername(userDto.getUsername())) {
            errors.put("username", "Tên tài khoản đã tồn tại!");
        }
        if(userRepository.existsByEmail(userDto.getEmail())) {
            errors.put("email", "Email đã được sử dụng để đăng ký tài khoản khác!");
        }
        if(userRepository.existsByPhone(userDto.getPhone())) {
            errors.put("phone", "đã được sử dụng để đăng ký tài khoản khác");
        }
        if(!errors.isEmpty()){
            throw new ValidateException(errors);
        }
        Optional<Role> role = roleService.findByName(userDto.getRoleName());
        User user = User.builder()
                .name(userDto.getName())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .gender(userDto.getGender())
                .birthday(userDto.getBirthday())
                .role(role.get())
                .build();

        User registerUser = userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(user);
        cartService.createCart(cart);
        return registerUser;
    }

    @Override
    public UserLoginResponse login(UserLoginDTO userLoginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtProvider.createToken(authentication);
            User user = (User) authentication.getPrincipal();
            UserLoginResponse userLoginResponse = UserLoginResponse.builder()
                    .message("Đăng nhập thành công")
                    .token(token)
                    .id(user.getId())
                    .name(user.getName())
                    .role(user.getAuthorities())
                    .build();
            return userLoginResponse;
        } catch (AuthenticationException e) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", "Tài khoản hoặc mật khẩu không chính xác");
            throw new ValidateException(errors);
        }
    }

    //Security
    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found -> username or password " + username));
        return user;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.get();
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

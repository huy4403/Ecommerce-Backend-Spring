package com.website.ecommerce.service.auth.impl;

import com.website.ecommerce.component.JwtProvider;
import com.website.ecommerce.dtos.authDTOs.AuthLoginDTO;
import com.website.ecommerce.dtos.authDTOs.AuthRegistrationDTO;
import com.website.ecommerce.exception.HandleException;
import com.website.ecommerce.model.Cart;
import com.website.ecommerce.model.Role;
import com.website.ecommerce.model.User;
import com.website.ecommerce.repository.UserRepository;
import com.website.ecommerce.response.UserLoginResponse;
import com.website.ecommerce.service.auth.AuthService;
import com.website.ecommerce.service.client.CartService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartService cartService;

    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    @Autowired
    public AuthServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User save(AuthRegistrationDTO userDto) {
        HashMap<String, String> errors = new HashMap<>();
        if(existsByUsername(userDto.getUsername())) {
            errors.put("username", "Tên tài khoản đã tồn tại!");
        }
        if(existsByEmail(userDto.getEmail())) {
            errors.put("email", "Email đã được sử dụng để đăng ký tài khoản khác!");
        }
        if(existsByPhone(userDto.getPhone())) {
            errors.put("phone", "đã được sử dụng để đăng ký tài khoản khác");
        }
        if(!errors.isEmpty()){
            throw new HandleException(errors);
        }
        Role role = Role.builder().id(2).build();
        User user = User.builder()
                .name(userDto.getName())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .address(userDto.getAddress())
                .gender(userDto.getGender())
                .birthday(userDto.getBirthday())
                .role(role)
                .build();

        User registerUser = userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(user);
        cartService.createCart(cart);
        return registerUser;
    }

    @Override
    public UserLoginResponse login(AuthLoginDTO authLoginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authLoginDTO.getUsername(), authLoginDTO.getPassword())
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
            throw new HandleException(errors);
        }
    }

    //Security
    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found -> username or password " + username));
        return user;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user;
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

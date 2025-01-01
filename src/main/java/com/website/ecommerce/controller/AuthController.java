package com.website.ecommerce.controller;

import com.website.ecommerce.dtos.userDTOs.UserLoginDTO;
import com.website.ecommerce.dtos.userDTOs.UserRegistrationDTO;
import com.website.ecommerce.model.User;
import com.website.ecommerce.response.UserLoginResponse;
import com.website.ecommerce.response.UserRegistrationResponse;
import com.website.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @ModelAttribute UserRegistrationDTO userDto) {
        User newUser = userService.save(userDto);
        return new ResponseEntity<>(
                new UserRegistrationResponse("User registered successfully",
                newUser.getId(),
                newUser.getUsername(),
                newUser.getPassword()),
                HttpStatus.OK
        );
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @ModelAttribute UserLoginDTO userDto) {
        UserLoginResponse userLoginResponse = userService.login(userDto);
        return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
    }
}

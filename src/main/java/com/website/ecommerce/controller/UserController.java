package com.website.ecommerce.controller;

import com.website.ecommerce.dtos.userDTOs.UserLoginDTO;
import com.website.ecommerce.dtos.userDTOs.UserRegistrationDTO;
import com.website.ecommerce.model.User;
import com.website.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @ModelAttribute UserRegistrationDTO userDto) {
        User newUser = userService.register(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @ModelAttribute UserLoginDTO userDto) {
        User userLogin = userService.login(userDto);
        return new ResponseEntity<>(userLogin, HttpStatus.OK);
    }
}

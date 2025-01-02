package com.website.ecommerce.controller.auth;

import com.website.ecommerce.dtos.authDTOs.AuthLoginDTO;
import com.website.ecommerce.dtos.authDTOs.AuthRegistrationDTO;
import com.website.ecommerce.model.User;
import com.website.ecommerce.response.UserLoginResponse;
import com.website.ecommerce.response.UserRegistrationResponse;
import com.website.ecommerce.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @ModelAttribute AuthRegistrationDTO userDto) {
        User newUser = authService.save(userDto);
        return new ResponseEntity<>(
                new UserRegistrationResponse("User registered successfully",
                newUser.getId(),
                newUser.getUsername(),
                newUser.getPassword()),
                HttpStatus.OK
        );
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @ModelAttribute AuthLoginDTO userDto) {
        UserLoginResponse userLoginResponse = authService.login(userDto);
        return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
    }
}

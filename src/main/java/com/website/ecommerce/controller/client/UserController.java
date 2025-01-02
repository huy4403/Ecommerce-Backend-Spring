package com.website.ecommerce.controller.client;

import com.website.ecommerce.dtos.userDTOs.UserUpdateDTO;
import com.website.ecommerce.model.User;
import com.website.ecommerce.service.client.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("api/user/info")
public class UserController {
    @Autowired
    private UserService userService;
    @PutMapping("{id}/update")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @ModelAttribute UserUpdateDTO userUpdateDTO) {
        userUpdateDTO.setId(id);
        User updatedUser = userService.update(userUpdateDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("{id}/show")
    public ResponseEntity<?> showUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

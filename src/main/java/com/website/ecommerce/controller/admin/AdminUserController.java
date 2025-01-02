package com.website.ecommerce.controller.admin;

import com.website.ecommerce.dtos.adminDTOs.AdminCreateUserDTO;
import com.website.ecommerce.dtos.adminDTOs.AdminUpdateUserDTO;
import com.website.ecommerce.model.User;
import com.website.ecommerce.service.admin.AdminUserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("api/admin/users")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("create")
    public ResponseEntity<?> createUser(@ModelAttribute AdminCreateUserDTO adminCreateUserDTO) {
        User newUser = adminUserService.save(adminCreateUserDTO);
        return ResponseEntity.ok("Thêm thành công người dùng: " + newUser.getUsername() + " với id: " + newUser.getId());
    }

    @PutMapping("{id}/update")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @ModelAttribute AdminUpdateUserDTO adminUpdateUserDTO) {
        adminUpdateUserDTO.setId(id);
        User updateUser = adminUserService.update(adminUpdateUserDTO);
        return ResponseEntity.ok(updateUser);
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = adminUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}/show")
    public ResponseEntity<?> showUser(@PathVariable Long id) {
        User user = adminUserService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("{id}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        adminUserService.deleteUserById(id);
        return ResponseEntity.ok("Xóa thành công người dùng: " + id);
    }

}

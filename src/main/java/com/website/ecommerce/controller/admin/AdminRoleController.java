package com.website.ecommerce.controller.admin;

import com.website.ecommerce.model.Role;
import com.website.ecommerce.service.admin.AdminRoleService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("api/admin/roles")
public class AdminRoleController {
    @Autowired
    private AdminRoleService adminRoleService;

    @PostMapping("/create")
    public ResponseEntity<?> createRole(@ModelAttribute Role role) {
        Role newRole = adminRoleService.save(role);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<?> updateRole(@ModelAttribute Role role, @PathVariable int id) {
        role.setId(id);
        Role updatedRole = adminRoleService.save(role);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<?> deleteRole(@PathVariable int id) {
        adminRoleService.delete(id);
        return new ResponseEntity<>("Xóa thành công quyền có id: " + id,HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAllRoles() {
        List<Role> roles = adminRoleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("{id}/show")
    public ResponseEntity<?> showRole(@PathVariable int id) {
        Role role = adminRoleService.getRoleById(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}

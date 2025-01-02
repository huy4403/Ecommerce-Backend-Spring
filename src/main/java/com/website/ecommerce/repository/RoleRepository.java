package com.website.ecommerce.repository;

import com.website.ecommerce.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
    Boolean existsByName(String name);
    @Query("SELECT COUNT(r) > 0 FROM Role r WHERE r.name = :name AND r.id != :id")
    Boolean existRoleByNameDiffId(String name, int id);
}

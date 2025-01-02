package com.website.ecommerce.repository;

import com.website.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    Boolean existsByPhone(String phone);
    Boolean existsByEmail(String email);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.username = :username AND u.id != :id")
    Boolean existUserByUsernameDiffUserId(String username, Long id);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.phone = :phone AND u.id != :id")
    Boolean existUserByPhoneDiffUserId(String phone, Long id);

    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email AND u.id != :id")
    Boolean existUserByEmailDiffUserId(String email, Long id);
}

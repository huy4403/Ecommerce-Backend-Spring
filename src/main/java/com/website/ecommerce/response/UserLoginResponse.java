package com.website.ecommerce.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Builder
@Data
public class UserLoginResponse {
    private String message;
    private Long id;
    private String token;
    private final String type = "Bearer";
    private String name;
    private Collection<? extends GrantedAuthority> role;
}

package com.website.ecommerce.component;


import com.website.ecommerce.exception.HandleException;
import com.website.ecommerce.model.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret = "secret";
    private int jwtExpiration = 60;

    public String createToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return Jwts.builder()
                .claim("id", user.getId())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public boolean validateToken(String token) {
        HashMap<String, String> errors = new HashMap<>();
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            errors.put("SignatureException", "Chữ ký JWT không hợp lệ");
        } catch (MalformedJwtException e) {
            errors.put("MalformedJwtException", "Token JWT không hợp lệ");
        } catch (ExpiredJwtException e) {
            errors.put("ExpiredJwtException", "Phiên đăng nhập đã hết hạn, vui lòng đăng nhập lại");
        } catch (UnsupportedJwtException e) {
            errors.put("UnsupportedJwtException", "Token JWT không được hỗ trợ");
        } catch (IllegalArgumentException e) {
            errors.put("IllegalArgumentException", "Chuỗi claims JWT trống");
        }

        if (!errors.isEmpty()) {
            throw new HandleException(errors);
        }
        return false;
    }

    public String getUserNameFromToken(String token) {
        String userName = Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().getSubject();
        return userName;
    }

    public Long getUserIdFromToken(String token) {
        Long userId = Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().get("id", Long.class);
        return userId;
    }
}

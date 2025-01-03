package com.website.ecommerce.component;

import com.website.ecommerce.model.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    private String jwtSecret = "secret";
    private int jwtExpiration = 86400;

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
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("Token has expired -> Message: {}", e.getMessage());
            throw e;
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported token -> Message: {}", e.getMessage());
            throw e;
        } catch (MalformedJwtException e) {
            logger.error("Invalid token -> Message: {}", e.getMessage());
            throw e;
        } catch (SignatureException e) {
            logger.error("Invalid token signature -> Message: {}", e.getMessage());
            throw e;
        } catch (IllegalArgumentException e) {
            logger.error("Token is missing or invalid -> Message: {}", e.getMessage());
            throw e;
        }
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

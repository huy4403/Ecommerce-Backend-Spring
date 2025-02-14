package com.website.ecommerce.filter;

import com.website.ecommerce.component.JwtProvider;
import com.website.ecommerce.model.User;
import com.website.ecommerce.service.auth.AuthService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = GetJwt(request);
            if (token != null) {
                jwtProvider.validateToken(token);  // validate trước để bắt lỗi nếu có

                String username = jwtProvider.getUserNameFromToken(token);
                User user = (User) authService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            exception(response, "Unauthorized", "Phiên đăng nhập đã hết hạn, vui lòng đăng nhập lại");
            return;
        } catch (UnsupportedJwtException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            exception(response, "Unsupported", "Token JWT không được hỗ trợ");
            return;
        } catch (MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            exception(response, "Invalid", "Token JWT không đúng định dạng");
            return;
        } catch (SignatureException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            exception(response, "Signature", "Chữ ký JWT không chính xác");
            return;
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            exception(response, "Unauthorized", "Vui lòng đăng nhập để thực hiện thao tác này");
            return;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            exception(response, "unexpected", "Đã xảy ra lỗi không mong muốn");
            return;
        }
        filterChain.doFilter(request, response);
    }
    private void exception(HttpServletResponse response, String typeErrors, String message) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\""+ typeErrors + "\": \"" + message +"\"}");
    }
    public String GetJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}

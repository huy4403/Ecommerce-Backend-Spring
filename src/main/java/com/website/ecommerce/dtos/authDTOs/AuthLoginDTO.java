package com.website.ecommerce.dtos.authDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthLoginDTO {

    @NotBlank(message = "Tên tài khoản không được để trống!")
    @Size(min = 5, max = 50, message = "Tên tài khoản phải có độ dài từ 5 đến 50 ký tự!")
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống!")
    @Size(min = 6, message = "Mật khẩu phải có độ dài từ 6 ký tự trở lên")
    private String password;

    public AuthLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthLoginDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

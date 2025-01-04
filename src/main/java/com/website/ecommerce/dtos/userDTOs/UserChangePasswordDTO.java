package com.website.ecommerce.dtos.userDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserChangePasswordDTO {

    @NotBlank(message = "Vui lòng nhập mật khẩu cũ!")
    private String oldPassword;

    @NotBlank(message = "Vui lòng nhập mật khẩu mới")
    @Size(min = 6, message = "Mật khẩu phải có độ dài từ 6 ký tự trở lên")
    private String password;

    @NotBlank(message = "Vui lòng xác nhận mật khẩu mới!")
    private String passwordconfirm;

    public UserChangePasswordDTO() {
    }

    public UserChangePasswordDTO(String oldPassword, String password, String passwordconfirm) {
        this.oldPassword = oldPassword;
        this.password = password;
        this.passwordconfirm = passwordconfirm;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordconfirm() {
        return passwordconfirm;
    }

    public void setPasswordconfirm(String passwordconfirm) {
        this.passwordconfirm = passwordconfirm;
    }
}

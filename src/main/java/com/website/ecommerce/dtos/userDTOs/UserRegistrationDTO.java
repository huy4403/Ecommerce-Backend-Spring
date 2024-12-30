package com.website.ecommerce.dtos.userDTOs;

import com.website.ecommerce.utils.customAnotation.userAnotation.PasswordConfirm;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@PasswordConfirm
public class UserRegistrationDTO {

    @NotBlank(message = "Tên không được để trống!")
    @Size(min = 3, message = "Tên phải có độ dài từ 3 ký tự trở lên!")
    @Pattern(regexp = "^[a-zA-ZàáạảãèéẹẻẽêếềệễỉíịìĩòóọỏõôốồổỗơớờởỡúùụủũưứừửữỳýỵỷỹđÀÁẠẢÃÈÉẸẺẼÊẾỀỆỄÌÍỊÌĨÒÓỌỎÕÔỐỒỔỖƠỚỜỞỠÚÙỤỦŨƯỨỪỬỮỲÝỴỶỸĐ ]+$", message = "Tên chỉ được chứa chữ cái (a-z, A-Z) và dấu cách!")
    private String name;

    @NotBlank(message = "Tên tài khoản không được để trống!")
    @Size(min = 5, max = 50, message = "Tên tài khoản phải có độ dài từ 5 đến 50 ký tự!")
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống!")
    @Size(min = 6, message = "Mật khẩu phải có độ dài từ 6 ký tự trở lên")
    private String password;

    @NotBlank(message = "Mật khẩu xác nhận không được để trống!")
    private String passwordcomfirm;

    @NotBlank(message = "Số điện thoại không được để trống!")
    @Pattern(
            regexp = "^(0\\d{9}|84\\d{9,10})$",
            message = "Số điện thoại sai định dạng!"
    )
    private String phone;

    @NotBlank(message = "Email không được để trống!")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Email không hợp lệ! Vui lòng nhập đúng định dạng email."
    )
    private String email;

    private String gender;

    @NotNull(message = "Vui lòng xác nhận ngày sinh")
    @Past(message = "Ngày sinh phải là một ngày trong quá khứ")
    private LocalDate birthday;

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(String name, String username, String password, String passwordcomfirm, String phone, String email, String gender, LocalDate birthday) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.passwordcomfirm = passwordcomfirm;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPasswordcomfirm() {
        return passwordcomfirm;
    }

    public void setPasswordcomfirm(String passwordcomfirm) {
        this.passwordcomfirm = passwordcomfirm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}

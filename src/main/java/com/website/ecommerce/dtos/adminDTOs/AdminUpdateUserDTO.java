package com.website.ecommerce.dtos.adminDTOs;

import com.website.ecommerce.model.Role;
import com.website.ecommerce.utils.customAnotation.userAnotation.PasswordConfirm;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class AdminUpdateUserDTO {

    private Long id;

    @NotBlank(message = "Tên không được để trống!")
    @Size(min = 3, message = "Tên phải có độ dài từ 3 ký tự trở lên!")
    @Pattern(
            regexp = "^[a-zA-ZàáâãèéêìíòóôõùúýăđĩũơưăằắẳẵặèẻẽềềếệỉịòỏõốồổỗờớởỡùụủũưứừửữỳýỵỷỹÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝĂĐĨŨƠƯĂẰẮẲẴẶÈẺẼỀỀẾỆỈỊÒỎÕỐỒỔỖỜỚỞỠÙỤỦŨƯỨỪỬỮỲÝỴỶỸ ]+$",
            message = "Tên chỉ được chứa chữ cái (a-z, A-Z) và dấu cách!"
    )
    private String name;

    @NotBlank(message = "Tên tài khoản không được để trống!")
    @Size(min = 5, max = 50, message = "Tên tài khoản phải có độ dài từ 5 đến 50 ký tự!")
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống!")
    @Size(min = 6, message = "Mật khẩu phải có độ dài từ 6 ký tự trở lên")
    private String password;

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

    @NotBlank(message = "Vui lòng nhập địa chỉ")
    private String address;

    @NotBlank(message = "Vui lòng chọn giới tính")
    private String gender;

    @NotNull(message = "Vui lòng xác nhận ngày sinh")
    @Past(message = "Ngày sinh phải là một ngày trong quá khứ")
    private LocalDate birthday;

    private Role role;

    public AdminUpdateUserDTO() {
    }

    public AdminUpdateUserDTO(Long id, String name, String username, String password, String phone, String email,String address, String gender, LocalDate birthday, Role role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.birthday = birthday;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

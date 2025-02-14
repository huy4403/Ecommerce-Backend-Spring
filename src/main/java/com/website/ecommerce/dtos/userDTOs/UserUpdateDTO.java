package com.website.ecommerce.dtos.userDTOs;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UserUpdateDTO {
    private Long id;

    @NotBlank(message = "Tên không được để trống!")
    @Size(min = 3, message = "Tên phải có độ dài từ 3 ký tự trở lên!")
    @Pattern(
            regexp = "^[a-zA-ZàáâãèéêìíòóôõùúýăđĩũơưăằắẳẵặèẻẽềềếệỉịòỏõốồổỗờớởỡùụủũưứừửữỳýỵỷỹÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝĂĐĨŨƠƯĂẰẮẲẴẶÈẺẼỀỀẾỆỈỊÒỎÕỐỒỔỖỜỚỞỠÙỤỦŨƯỨỪỬỮỲÝỴỶỸ ]+$",
            message = "Tên chỉ được chứa chữ cái (a-z, A-Z) và dấu cách!"
    )
    private String name;

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

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(Long id, String name, String phone, String email,String address, String gender, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.birthday = birthday;
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
}

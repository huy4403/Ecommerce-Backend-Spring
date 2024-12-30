package com.website.ecommerce.utils.customAnotation.userAnotation;

import com.website.ecommerce.dtos.userDTOs.UserRegistrationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConfirmValidator implements ConstraintValidator<PasswordConfirm, UserRegistrationDTO> {

    @Override
    public void initialize(PasswordConfirm constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserRegistrationDTO userDTO, ConstraintValidatorContext context) {
        if (userDTO == null) {
            return true;  // Tránh NullPointerException
        }

        String password = userDTO.getPassword();
        String confirmPassword = userDTO.getPasswordcomfirm();

        if (password != null && confirmPassword != null && !password.equals(confirmPassword)) {
            // Thêm thông báo lỗi cho trường passwordcomfirm
            context.buildConstraintViolationWithTemplate("Mật khẩu xác nhận không khớp với mật khẩu!")
                    .addPropertyNode("passwordcomfirm")  // Đánh dấu trường bị lỗi
                    .addConstraintViolation();
            return false;  // Kiểm tra không hợp lệ
        }

        return true;  // Kiểm tra hợp lệ
    }
}
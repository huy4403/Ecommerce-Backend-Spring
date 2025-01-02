package com.website.ecommerce.utils.customAnotation.userAnotation;

import com.website.ecommerce.dtos.authDTOs.AuthRegistrationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConfirmValidator implements ConstraintValidator<PasswordConfirm, AuthRegistrationDTO> {

    @Override
    public void initialize(PasswordConfirm constraintAnnotation) {
    }

    @Override
    public boolean isValid(AuthRegistrationDTO userDTO, ConstraintValidatorContext context) {
        if (userDTO == null) {
            return true;
        }

        String password = userDTO.getPassword();
        String confirmPassword = userDTO.getPasswordcomfirm();

        if (password != null && confirmPassword != null && !password.equals(confirmPassword)) {
            context.buildConstraintViolationWithTemplate("Mật khẩu xác nhận không khớp với mật khẩu!")
                    .addPropertyNode("passwordcomfirm")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
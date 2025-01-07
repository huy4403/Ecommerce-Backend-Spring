package com.website.ecommerce.utils.customAnotation.userAnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordConfirmValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConfirm {
    String message() default "Mật khẩu xác nhận không khớp với mật khẩu!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
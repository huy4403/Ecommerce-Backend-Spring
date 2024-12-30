package com.website.ecommerce.customAnotation.productAnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ComparePriceValidator.class)  // Đánh dấu validator mà bạn sẽ tạo
@Target({ ElementType.TYPE })  // Áp dụng cho class (TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComparePrice {
    String message() default "Giá bán phải lớn hơn giá nhập!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

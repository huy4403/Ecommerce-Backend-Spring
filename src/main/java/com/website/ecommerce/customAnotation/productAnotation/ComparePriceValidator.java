package com.website.ecommerce.customAnotation.productAnotation;

import com.website.ecommerce.dtos.productDTOs.ProductCreateDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ComparePriceValidator implements ConstraintValidator<ComparePrice, ProductCreateDTO> {

    @Override
    public void initialize(ComparePrice constraintAnnotation) {
    }
    @Override
    public boolean isValid(ProductCreateDTO productDto, ConstraintValidatorContext context) {
        if (productDto == null) {
            return true;  // Tránh NullPointerException
        }
        Integer price = productDto.getPrice();
        Integer importPrice = productDto.getImportPrice();
        if (importPrice != null && price != null && importPrice >= price) {
            // Thêm thông báo lỗi cho trường passwordcomfirm
            context.buildConstraintViolationWithTemplate("Giá bán phải lớn hơn giá nhập!")
                    .addPropertyNode("price")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}

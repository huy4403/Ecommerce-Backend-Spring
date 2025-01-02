package com.website.ecommerce.dtos.adminDTOs;

import com.website.ecommerce.model.Category;
import com.website.ecommerce.utils.customAnotation.productAnotation.ComparePrice;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@ComparePrice
public class AdminCreateProductDTO {

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 5, message = "Tên sản phẩm phải dài hơn 5 ký tự")
    private String name;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private Integer quantity;

    @NotNull(message = "Giá nhập sản phẩm không được để trống")
    @Min(value = 1, message = "Giá nhập sản phẩm không được bằng 0")
    private Integer importPrice;

    @NotNull(message = "Giá nhập sản phẩm không được để trống")
    @Min(value = 1, message = "Giá nhập sản phẩm không được bằng 0")
    private Integer price;

    @NotBlank(message = "Vui lòng nhập mô tả sản phẩm")
    private String description;

    @NotNull(message = "Vui lòng chọn danh mục cho sản phẩm")
    private Category category;

    public AdminCreateProductDTO(String name, Integer quantity, Integer importPrice, Integer price, String description, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.importPrice = importPrice;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public AdminCreateProductDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(Integer importPrice) {
        this.importPrice = importPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

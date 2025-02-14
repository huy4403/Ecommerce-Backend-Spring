package com.website.ecommerce.response;

import lombok.Builder;
import lombok.Data;

@Builder
public class OrderItemResponse {
    private Long id;
    private String productImg;
    private String productName;
    private double productPrice;
    private int quantityBuy;
    private double totalPrice;

    public OrderItemResponse(Long id, String productImg, String productName, double productPrice, int quantityBuy, double totalPrice) {
        this.id = id;
        this.productImg = productImg;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantityBuy = quantityBuy;
        this.totalPrice = totalPrice;
    }

    public OrderItemResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantityBuy() {
        return quantityBuy;
    }

    public void setQuantityBuy(int quantityBuy) {
        this.quantityBuy = quantityBuy;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

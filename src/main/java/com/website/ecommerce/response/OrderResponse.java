package com.website.ecommerce.response;

import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public class OrderResponse {

    private String userName;
    private String userPhone;
    private String userAddress;
    private double total;
    private String status;
    private String paymentMethod;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> orderItemResponses;

    public OrderResponse() {
    }

    public OrderResponse(String userName, String userPhone, String userAddress, double total, String status, String paymentMethod, LocalDateTime createdAt, List<OrderItemResponse> orderItemResponses) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.total = total;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
        this.orderItemResponses = orderItemResponses;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderItemResponse> getOrderItemResponses() {
        return orderItemResponses;
    }

    public void setOrderItemResponses(List<OrderItemResponse> orderItemResponses) {
        this.orderItemResponses = orderItemResponses;
    }
}

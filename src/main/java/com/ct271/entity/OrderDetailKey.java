package com.ct271.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderDetailKey implements Serializable {

    @Column(name = "product_id")
    Long productId;

    @Column(name = "order_id")
    Long orderId;

    public OrderDetailKey() {
    }

    public OrderDetailKey(Long productId, Long orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}

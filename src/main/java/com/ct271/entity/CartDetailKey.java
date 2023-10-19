package com.ct271.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CartDetailKey implements Serializable {
    @Column(name = "product_id")
    Long productId;

    @Column(name = "cart_id")
    Long cartId;

    public CartDetailKey() {
    }

    public CartDetailKey(Long productId, Long cartId) {
        this.productId = productId;
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}

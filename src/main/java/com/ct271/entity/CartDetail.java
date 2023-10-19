package com.ct271.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "cart_detail")
public class CartDetail {
    @EmbeddedId
    private CartDetailKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;

    private int numberCart;

    private Long totalPrice;

    public CartDetail() {
    }

    public CartDetail( Product product, Cart cart, Long totalPrice) {
        this.product = product;
        this.cart = cart;
        this.numberCart = 1;
        this.totalPrice = totalPrice;
    }

    public CartDetail(Product product, Cart cart, int numberCart, Long totalPrice) {
        this.product = product;
        this.cart = cart;
        this.numberCart = numberCart;
        this.totalPrice = totalPrice;
    }

    public CartDetailKey getId() {
        return id;
    }

    public void setId(CartDetailKey id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getNumberCart() {
        return numberCart;
    }

    public void setNumberCart(int numberCart) {
        this.numberCart = numberCart;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}

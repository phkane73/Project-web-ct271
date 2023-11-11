package com.ct271.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {

    @EmbeddedId
    private OrderDetailKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    @JsonBackReference(value = "order_value")
    private Orders order;

    private Integer number;

    @Column(name = "total_price_one")
    private Long totalPriceOne;

    public OrderDetail() {
    }

    public OrderDetail(Product product, Orders order, Integer number, Long totalPriceOne) {
        this.product = product;
        this.order = order;
        this.number = number;
        this.totalPriceOne = totalPriceOne;
    }

    public OrderDetailKey getId() {
        return id;
    }

    public void setId(OrderDetailKey id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getTotalPriceOne() {
        return totalPriceOne;
    }

    public void setTotalPriceOne(Long totalPriceOne) {
        this.totalPriceOne = totalPriceOne;
    }
}

package com.ct271.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.util.Set;


@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private String address;

    private String date;

    @Column(name = "total_price")
    private Long totalPrice;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonBackReference(value = "user_order")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "order_value")
    private Set<OrderDetail> orderDetails;

    public Orders() {
        super();
    }

    public Orders(String address, String date, Long totalPrice, Integer status, User user, Set<OrderDetail> orderDetails) {
        this.address = address;
        this.date = date;
        this.totalPrice = totalPrice;
        this.status = status;
        this.user = user;
        this.orderDetails = orderDetails;
    }

    public Orders(String address, String date, Long totalPrice, Integer status, User user) {
        this.address = address;
        this.date = date;
        this.totalPrice = totalPrice;
        this.status = status;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}

package com.ct271.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;
	private String productName;
	private int price;
	private int number;
	private String brand;
	private String categoryName;
	private String imageProductInfor;

	private int isDeleted;

	@ManyToOne
	@JoinColumn(name = "configure_id", referencedColumnName = "configure_id")
	@JsonManagedReference
	private Configure configure;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<ProductImage> productImages;

	@OneToMany(mappedBy = "product")
	@JsonManagedReference(value = "cart_product")
	private Set<CartDetail> cartDetails;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<OrderDetail> orderDetails;

	public Product() {
		super();
	}

	public Product(String productName, int price, int number, String brand, String categoryName, String imageProductInfor, Configure configure, Set<ProductImage> productImages, Set<CartDetail> cartDetails, Set<OrderDetail> orderDetails) {
		this.productName = productName;
		this.price = price;
		this.number = number;
		this.brand = brand;
		this.categoryName = categoryName;
		this.imageProductInfor = imageProductInfor;
		this.configure = configure;
		this.productImages = productImages;
		this.cartDetails = cartDetails;
		this.orderDetails = orderDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImageProductInfor() {
		return imageProductInfor;
	}

	public void setImageProductInfor(String imageProductInfor) {
		this.imageProductInfor = imageProductInfor;
	}

	public Configure getConfigure() {
		return configure;
	}

	public void setConfigure(Configure configure) {
		this.configure = configure;
	}

	public Set<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(Set<ProductImage> productImages) {
		this.productImages = productImages;
	}

	public Set<CartDetail> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(Set<CartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
}

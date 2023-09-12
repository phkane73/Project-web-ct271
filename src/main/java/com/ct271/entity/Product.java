package com.ct271.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;
	private String productName;
	private String price;
	private String number;
	private String brand;
	private String categoryName;
	private String imageProductInfor;
	
	@ManyToOne
	@JoinColumn(name = "configure_id", referencedColumnName = "configure_id")
	@JsonManagedReference
	private Configure configure;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<ProductImage> productImages;
	
	public Product() {
		super();
	}

	public Product(String productName, String price, String number, String brand, String categoryName,
			String imageProductInfor, Configure configure, Set<ProductImage> productImages) {
		super();
		this.productName = productName;
		this.price = price;
		this.number = number;
		this.brand = brand;
		this.categoryName = categoryName;
		this.imageProductInfor = imageProductInfor;
		this.configure = configure;
		this.productImages = productImages;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
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
}

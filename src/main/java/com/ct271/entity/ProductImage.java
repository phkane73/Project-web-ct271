package com.ct271.entity;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_img_id")
	private int id;
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false, referencedColumnName = "product_id")
	@JsonManagedReference
	private Product product;

	public ProductImage() {
		super();
	}

	public ProductImage(String image, Product product) {
		super();
		this.image = image;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Transient
    public String getPhotosImagePath() {
        if(image == null) return null;        
        return "./images/" + id + "/" + image;
    }
	
	@Transient
	public String getPhotosImageInforPath() {
		if(image == null) return null;        
		return "./images/" + id + "/" + "infor/" + image;
	}
	
	
}

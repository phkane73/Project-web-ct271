package com.ct271.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.generator.Generator;
import org.springframework.data.annotation.Transient;

@Entity
public class ProductImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_img_id")
	private int id;
	private String image;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false, referencedColumnName = "product_id")
	@JsonBackReference
	private Product product;

	public ProductImage() {
		super();
	}

	public ProductImage(String image, Product product) {
		super();
		this.image = image;
		this.product = product;
	}

	@Transient
	public String getPhotosImagePath() {
		if (image == null) return null;
		return "./images/" + id + "/" + image;
	}

	@Transient
	public String getPhotosImageInforPath() {
		if (image == null) return null;
		return "./images/" + id + "/" + "infor/" + image;
	}


	public void setId(int id) {
		this.id = id;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getId() {
		return this.id;
	}

	public String getImage() {
		return this.image;
	}

	public Product getProduct() {
		return this.product;
	}
}

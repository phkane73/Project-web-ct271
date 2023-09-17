package com.ct271.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	private String username;
	private String email;
	private String phone;
	private String address;
	private String password;
	private int role;
	
	public User() {
		super();
	}

	public User(String username, String email, String phone, String address, String password, int role) {
		super();
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.password = password;
		this.role = role;
	}
}

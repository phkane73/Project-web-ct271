package com.ct271.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	@NotBlank(message="Tên không thể bỏ trống")
	private String username;
	@Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email không hợp lệ")
	private String email;
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Số điện thoại không hợp lệ")
	private String phone;
	@NotBlank(message="Địa chỉ không thể bỏ trống")
	private String address;
	@Size(min=6, message="Mật khẩu phải từ 6 đến 30 ký tự")
	private String password;
	private int role;
	@OneToOne(mappedBy = "user")
	@JsonIgnore
	private Cart cart;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Set<Orders> orders;

	public User() {
	}

	public User(String username, String email, String phone, String address, String password) {
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.password = password;
	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User(String username, String email, String phone, String address, String password, int role) {
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}
}

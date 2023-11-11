package com.ct271.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ct271.entity.User;

import jakarta.servlet.http.HttpSession;

public interface IUserService {
	
	//Thêm tài khoản vào database
	public User addUser(User user);
	
	//Đăng kí tài khoản (Kiểm tra xem tài khoản đã tồn tại chưa)
	public User userRegister(User user);
	
	//Đăng nhâp
	public User userLogin(User user);

	//Đăng xuất tài khoản
	public void userLogout(HttpSession session);

	//Lấy ra 1 User
	public Optional<User> getUser(Long id);
	
	//Lấy ra danh sách User
	public List<User> getAllUser();

	//Phân trang
	Page<User> findAll(Pageable pageable);

	//Sort
	List<User> findAll(Sort sort);

	public Optional<User> findById(Long user_id);

	//Lấy ra số user
	long getTotalElement();
	
}

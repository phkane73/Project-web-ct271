package com.ct271.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ct271.encrypt.Encrypt;
import com.ct271.entity.User;
import com.ct271.repository.IUserRepo;

import jakarta.servlet.http.HttpSession;


@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepo userRepo;

	@Override
	public User addUser(User user) {
		User newuser = new User(user.getUsername(), user.getEmail(), user.getPhone(), user.getAddress(),
				Encrypt.toSHA1(user.getPassword()), user.getRole());
			return userRepo.save(newuser);
	}

	@Override
	public User userRegister(User user) {
		User oldUser = userRepo.findByEmail(user.getEmail());
		if (oldUser == null) {	
			return user;
		}
		return null;
	}

	@Override
	public int userLogin(User user, HttpSession session) {
		User u = userRepo.findByEmail(user.getEmail());
		if (u != null) {
			if (u.getPassword().equals(Encrypt.toSHA1(user.getPassword()))) {			
				session.setAttribute("name", u.getUsername());
				return u.getRole();
			}
			return 2;
		}
		return 2;
	}

	@Override
	public Optional<User> getUser(Long id) {
		return userRepo.findById(id);
	}

	@Override
	public List<User> getAllUser() {
		return userRepo.findAll();
	}

	@Override
	public int userLogout(HttpSession session) {
		session.removeAttribute("name");
		session.removeAttribute("admin");
		return 0;
	}
	
	@Override
	public List<User> findAll(Sort sort) {
		return userRepo.findAll(sort);
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepo.findAll(pageable);
	}
	
	@Override
	public long getTotalElement() {
		return userRepo.count();
	}

}

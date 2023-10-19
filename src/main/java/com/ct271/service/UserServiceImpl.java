package com.ct271.service;

import com.ct271.encrypt.Encrypt;
import com.ct271.entity.Cart;
import com.ct271.entity.User;
import com.ct271.repository.ICartRepo;
import com.ct271.repository.IUserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements IUserService {

	private final IUserRepo userRepo;

	private final ICartRepo iCartRepo;

	public UserServiceImpl(IUserRepo userRepo, ICartRepo iCartRepo) {
		this.userRepo = userRepo;
		this.iCartRepo = iCartRepo;
	}

	@Override
	public User addUser(User user) {
		User newuser = new User(user.getUsername(), user.getEmail(), user.getPhone(), user.getAddress(),
				Encrypt.toSHA1(user.getPassword()), user.getRole());
		Cart cart = new Cart();
		cart.setUser(newuser);
		iCartRepo.save(cart);
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
	public User userLogin(User user) {
		User u = userRepo.findByEmail(user.getEmail());
		if (u != null) {
			if (u.getPassword().equals(Encrypt.toSHA1(user.getPassword()))) {
				return u;
			}
		}
		return null;
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
	public void userLogout(HttpSession session) {
		session.removeAttribute("admin");
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

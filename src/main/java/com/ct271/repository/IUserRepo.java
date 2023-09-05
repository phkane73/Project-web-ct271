package com.ct271.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ct271.entity.User;

@Repository
public interface IUserRepo extends JpaRepository<User, Long>{
	public User findByEmail(String email);
}

package com.ct271.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ct271.entity.User;
import com.ct271.service.IUserService;

@CrossOrigin(origins = "*")
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserRestController {
	private final IUserService iUserService;

	@GetMapping("/users")
	public ResponseEntity<?> status() {
		List<User> users = iUserService.getAllUser();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}




}

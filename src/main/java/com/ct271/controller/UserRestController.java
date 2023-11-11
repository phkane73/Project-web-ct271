package com.ct271.controller;

import com.ct271.entity.Cart;
import com.ct271.entity.User;
import com.ct271.repository.ICartRepo;
import com.ct271.service.IUserService;
import jakarta.mail.Message;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("/user")
@RestController
public class UserRestController {
	private final IUserService iUserService;

	private final ICartRepo iCartRepo;

	public UserRestController(IUserService iUserService, ICartRepo iCartRepo) {
		this.iUserService = iUserService;
		this.iCartRepo = iCartRepo;
	}

	@PostMapping ("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		User AuthUser = iUserService.userLogin(user);
		if(AuthUser == null){
			return new ResponseEntity<>("Tài khoản hoặc mật khẩu không chính xác!", HttpStatus.BAD_REQUEST);
		}
		Cart cart = iCartRepo.findCartByUserId(AuthUser.getId());
		return new ResponseEntity <>(cart, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult bindingResult)  {
		if (bindingResult.hasErrors()){
			return new ResponseEntity <>(bindingResult.getFieldErrors(),HttpStatus.OK);
		}
		if(iUserService.userRegister(user)==null){
			return new ResponseEntity <>(1,HttpStatus.OK);
		}
		iUserService.addUser(user);
		return new ResponseEntity <>(200,HttpStatus.OK);
	}
}

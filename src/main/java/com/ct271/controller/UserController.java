package com.ct271.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ct271.entity.User;
import com.ct271.request.RegisterRequest;
import com.ct271.service.IProductService;
import com.ct271.service.IUserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class UserController {

	@Autowired
	private IUserService iUserService;
	
	@Autowired
	private IProductService iProductService;
	
	
	@GetMapping("/homePage")
	public String showHomePage() {
		return "UserPage/index";
	}
	
	
	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("registerrequest", new RegisterRequest("","","","",""));
		return "UserPage/register";
	}
	
	@PostMapping("/register")
	public String submitFormRegister(Model model,
			@Valid @ModelAttribute("registerrequest") RegisterRequest registerRequest, BindingResult result) {
			User newuser = new User(registerRequest.username(), registerRequest.email(), registerRequest.phone(), 
					registerRequest.address(),registerRequest.password(), 1);
		if (iUserService.userRegister(newuser) == null && !result.hasErrors()) {
			model.addAttribute("messageError", "Tài khoản đã tồn tại");
			return "UserPage/register";
		}
		if(result.hasErrors()) {
			model.addAttribute("messageError", "Vui lòng nhập đúng các thông tin");
			return "UserPage/register";
		}
		iUserService.addUser(newuser);
		model.addAttribute("messageSuccess", "Đăng ký tài khoản thành công");
		model.addAttribute("registerrequest", new RegisterRequest("","","","",""));
		return "UserPage/register";
	}
	
	@GetMapping("/login")
	public String showLogin(User user,Model model) {
		model.addAttribute("user", user);
		return "UserPage/login";
	}
	
	@PostMapping("/login")
	public String submitFormLogin(@ModelAttribute("user") User user, Model model, HttpSession session) {
		if(iUserService.userLogin(user,session) == 2) {		
			model.addAttribute("message", "Tài khoản hoặc mật khẩu không chính xác");
			return "UserPage/login";
		}else if(iUserService.userLogin(user,session) == 1){	
			return "redirect:/homePage";			
		}
		session.setAttribute("admin", 0);
		return "redirect:/adminPage/next?namePage=overview";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("name");
		session.removeAttribute("admin");
		return "redirect:/login";
	}
	
	@GetMapping("/adminPage/page")
	public String paginate(Model model, @RequestParam("p") Optional<Integer> p, HttpSession session) {
		long allUser = iUserService.getTotalElement();
		int	numberElementOfPage = 9;
		int numberPage = (int)allUser/numberElementOfPage;
		if(numberPage <= 1) {
			numberPage = 1;	
		}
		if(session.getAttribute("admin") != null) {		
			Pageable pageable = PageRequest.of(p.orElse(0), numberElementOfPage);
			Page<User> page = iUserService.findAll(pageable);
			int[] numberPageArr = new int[numberPage];
			for(int i=0; i<numberPage; i++) {
				numberPageArr[i] = i;
			}
			model.addAttribute("listUser", page);
			model.addAttribute("numberPage", numberPageArr);
			model.addAttribute("alluser", allUser);
			model.addAttribute("currentPage", p.get());
			model.addAttribute("namePage","list users");
			return "AdminPage/index";
		}
		return "redirect:/login";
	}
		
	@GetMapping("/adminPage/next")
	public String nextPage(Model model,@RequestParam("namePage") String namePage,HttpSession session) {
		if(session.getAttribute("admin") != null) {		
			if(namePage.equals("addproduct")) {
				model.addAttribute("namePage","add product");
				return "AdminPage/index";
			}
			if(namePage.equals("overview")) {
				long users = iUserService.getTotalElement();
				long products = iProductService.getTotalElement();
				model.addAttribute("allUsers", users);
				model.addAttribute("allProducts", products);
				model.addAttribute("namePage","overview");
				return "AdminPage/index";	
			}
		}
		return "redirect:/login";
	}
}

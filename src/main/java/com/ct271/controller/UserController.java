package com.ct271.controller;
//Controller xử lý về user
//Controller đáp ứng tài nguyên cho template engine thymeleaf chạy server side rendering

import com.ct271.entity.User;
import com.ct271.service.IOrderService;
import com.ct271.service.IProductService;
import com.ct271.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class UserController {

	private final IUserService iUserService;
	private final IProductService iProductService;

	private final IOrderService iOrderService;

	public UserController(IUserService iUserService, IProductService iProductService, IOrderService iOrderService) {
		this.iUserService = iUserService;
		this.iProductService = iProductService;
		this.iOrderService = iOrderService;
	}
	//Hiển thị form login
	@GetMapping("/login")
	public String showLogin(User user, Model model) {
		model.addAttribute("user", user);
		return "AdminPage/login";
	}

	//Xử lý login được gọi hàm userLogin từ /service/UserServiceImpl
	@PostMapping("/login")
	public String submitFormLogin(@ModelAttribute("user") User user, Model model, HttpSession session) {
		User User = iUserService.userLogin(user);
		if(User.getRole() == 0){
			session.setAttribute("admin", "admin");
			return "redirect:/adminPage/next?namePage=overview";
		}
		model.addAttribute("message", "Tài khoản hoặc mật khẩu không chính xác");
		return "AdminPage/login";

	}

	//Xử lý đăng xuất tài khoản
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//Xóa session
		iUserService.userLogout(session);
		return "redirect:/login";
	}

	//Phan trang user management
	@GetMapping("/adminPage/page")
	public String paginate(Model model, @RequestParam("p") Optional<Integer> p, HttpSession session) {
		//Tổng số user
		long allUser = iUserService.getTotalElement();
		//Số user trong 1 trang
		int numberElementOfPage = 9;
		//Số trang
		int numberPage = (int) allUser / numberElementOfPage;
		//Khi số user chưa vượt quá 1 trang
		if (numberPage <= 1) {
			//Gáng số trang = 1
			numberPage = 1;
		}
		//Kiểm tra session admib
		if (session.getAttribute("admin") != null) {
			//Xử lý phân trang
			Pageable pageable = PageRequest.of(p.orElse(0), numberElementOfPage);
			Page<User> page = iUserService.findAll(pageable);
			int[] numberPageArr = new int[numberPage];
			for (int i = 0; i < numberPage; i++) {
				numberPageArr[i] = i;
			}
			model.addAttribute("listUser", page);
			model.addAttribute("numberPage", numberPageArr);
			model.addAttribute("alluser", allUser);
			model.addAttribute("currentPage", p.get());
			model.addAttribute("namePage", "list users");
			return "AdminPage/index";
		}
		return "redirect:/login";
	}

	//xử lý chuyển trang giữa các trang trong admin page
	@GetMapping("/adminPage/next")
	public String nextPage(Model model, @RequestParam("namePage") String namePage, HttpSession session) {
		//Phải là admin mới cho phép vào admin page
		if (session.getAttribute("admin") != null) {
			//Replace component tương ứng với namePage
			if (namePage.equals("addproduct")) {
				model.addAttribute("namePage", "add product");
				return "AdminPage/index";
			}
			if (namePage.equals("overview")) {
				//Hiển thị tổng số user và product trong trang overview
				long users = iUserService.getTotalElement();
				long products = iProductService.getTotalElement();
				long orders = iOrderService.getTotalElement();
				model.addAttribute("allUsers", users);
				model.addAttribute("allProducts", products);
				model.addAttribute("allOrders", orders);
				model.addAttribute("namePage", "overview");
				return "AdminPage/index";
			}
		}
		return "redirect:/login";
	}
}

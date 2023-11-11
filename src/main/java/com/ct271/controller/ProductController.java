package com.ct271.controller;
//Controller này dùng để xử lý về phần thêm xửa xóa sản phẩm
//Controller đáp ứng tài nguyên cho template engine thymeleaf chạy server side rendering

import com.ct271.DeleteFile;
import com.ct271.FileUploadUtil;
import com.ct271.entity.Configure;
import com.ct271.entity.Orders;
import com.ct271.entity.Product;
import com.ct271.entity.ProductImage;
import com.ct271.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

	private final IProductService iProductService;

	private final IConfigureService iConfigureService;

	private final IProductImageService iProductImageService;

	private final IOrderService iOrderService;

	private final ICartDetailService iCartDetailService;

	public ProductController(IProductService iProductService, IConfigureService iConfigureService, IProductImageService iProductImageService, IOrderService iOrderService, ICartDetailService iCartDetailService) {
		this.iProductService = iProductService;
		this.iConfigureService = iConfigureService;
		this.iProductImageService = iProductImageService;
		this.iOrderService = iOrderService;
		this.iCartDetailService = iCartDetailService;
	}

	//Xử lý hiện form thêm từng loại sản phẩm khi truy cập vào /product/category
	@GetMapping("/product/category")
	public String showAddProduct(Model model, @RequestParam("namePage") String namePage) {
		//Tạo 1 model product
		Product product = new Product();
		//Đưa model product xuống thymeleaf
		model.addAttribute("product", product);
		//Nếu Param namePage là tablet (thêm tablet)
		if (namePage.equals("tablet")) {
			//Tạo 1 list các hãng tablet
			List<String> brandList = Arrays.asList("Apple iPad", "Samsung", "Xiaomi", "OPPO");
			//Tạo 1 list các option dung luong luu tru
			List<String> diskSpaceList = Arrays.asList("16GB", "32GB", "64GB", "128GB", "256GB", "512GB", "1T", "2T");
			//Hiển thị ra giao diện
			model.addAttribute("brandList", brandList);
			model.addAttribute("diskSpaceList", diskSpaceList);
			//Trả về 1 namePage vơi tên là tablet để index.html có thể nhận được giá trị để gọi fragment theo đúng với loại đưa vào
			model.addAttribute("namePage", "tablet");
			return "AdminPage/index";
		}
		//Nếu Param namePage là laptop (thêm laptop)
		if (namePage.equals("laptop")) {
			//Tạo 1 list các hãng labtop
			List<String> brandList = Arrays.asList("MacBook", "hp", "Asus", "Dell", "Acer", "Lenovo");
			//Hiển thị ra giao diện
			model.addAttribute("brandList", brandList);
			//Trả về 1 namePage vơi tên là tablet để index.html có thể nhận được giá trị
			// để gọi fragment theo đúng với loại đưa vào
			model.addAttribute("namePage", "laptop");
			return "AdminPage/index";
		}
		//Nếu Param namePage là smartwatch (thêm smartwatch)
		if (namePage.equals("smartwatch")) {
			//Tạo 1 list các hãng smartwatch
			List<String> brandList = Arrays.asList("Apple", "Samsung", "Xiaomi");
			//Hiển thị ra giao diện
			model.addAttribute("brandList", brandList);
			//Trả về 1 namePage vơi tên là tablet để index.html có thể nhận được giá
			// trị để gọi fragment theo đúng với loại đưa vào
			model.addAttribute("namePage", "smartwatch");
			return "AdminPage/index";
		}
		//Nếu Param namePage là earphone (thêm earphone)
		if (namePage.equals("earphone")) {
			//Tạo 1 list các hãng smartwatch
			List<String> brandList = Arrays.asList("Apple", "Samsung", "SONY", "OPPO");
			//Hiển thị ra giao diện
			model.addAttribute("brandList", brandList);
			//Trả về 1 namePage vơi tên là tablet để index.html có thể nhận được giá
			// trị để gọi fragment theo đúng với loại đưa vào
			model.addAttribute("namePage", "earphone");
			return "AdminPage/index";
		}
		return "AdminPage/index";
	}

	//Xử lý khi thuc hiện thao tác submit form thêm sản phẩm
	@PostMapping("/product/category")
	public String addProduct(@RequestParam("namePage") String namePage,
							 @ModelAttribute("product") Product product, @RequestParam("photos") MultipartFile[] photos,
							 @RequestParam("imageInfor") MultipartFile imageInfor) throws IOException {
		//Tạo đối tượng configure và gáng dữ liệu thêm các configure
		//Việc xử lí thêm configure(addConfigure) đươc thực hiện ở /service/ProductServiceImpl
		Configure configure = iConfigureService.addConfigure(product);
		//Set cấu hình của sản phẩm
		product.setConfigure(configure);
		//Set loại sản phẩm
		product.setCategoryName(namePage);
		//Set trạng thái xóa(chua xoa)
		product.setIsDeleted(0);
		//Set avatar của sản phẩm
		product.setImageProductInfor(imageInfor.getOriginalFilename());
		//Lưu sản phẩm vào trong cơ sở dữ liệu
		//Được thực hiện ở /service/ProductServiceImpl
		iProductService.addProduct(product);
		//Tạo chuỗi dir lưu ảnh được tải lên (ảnh infor)
		String filenameInfor = StringUtils.cleanPath(imageInfor.getOriginalFilename());
		//Đường dẫn lưu ảnh infor để đáp ứng cho frontend khi restAPI
		String uploadDirInforFE = "/NienLuan/frontendProjectCt271/projectCt271Fe/public/images/" + product.getId() + "/infor/";
		//Đường dẫn lưu ảnh infor để đáp ứng cho frontend khi sử dụng thymeleaf
		String uploadDirInforBE = "./src/main/resources/static/images/" + product.getId() + "/infor/";
		//Save images
		// Được thực hiện ở /FileUploadUtil
		FileUploadUtil.saveFile(uploadDirInforFE, filenameInfor, imageInfor);
		FileUploadUtil.saveFile(uploadDirInforBE, filenameInfor, imageInfor);
		//Duyệt và xử lý các ảnh detail của sản phẩm
		Arrays.asList(photos).stream().forEach(photo -> {
			//Tạo 1 đối tượng images detail
			ProductImage productImages = new ProductImage();
			//Set ảnh duyệt vao đối tượng
			productImages.setImage(photo.getOriginalFilename());
			//Set product để nhận dạng ảnh chi tiết đó của sản phẩm nào
			productImages.setProduct(product);
			//Lưu vào database
			iProductImageService.addImage(productImages);
			//Bước tạo path luu ảnh vào thư mục như ở trên
			String filename = StringUtils.cleanPath(photo.getOriginalFilename());
			String uploadDir = "/NienLuan/frontendProjectCt271/projectCt271Fe/public/images/" + product.getId();
			String uploadDirBE = "./src/main/resources/static/images/" + product.getId();
			try {
				FileUploadUtil.saveFile(uploadDir, filename, photo);
				FileUploadUtil.saveFile(uploadDirBE, filename, photo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		//Trả về giao diện tương ứng tùng loại sản phẩm mà ta tương tác
		if (namePage.equals("tablet")) {
			return "redirect:/product/category?namePage=tablet";
		}
		if (namePage.equals("laptop")) {
			return "redirect:/product/category?namePage=laptop";
		}
		if (namePage.equals("smartwatch")) {
			return "redirect:/product/category?namePage=smartwatch";
		}
		if (namePage.equals("earphone")) {
			return "redirect:/product/category?namePage=earphone";
		}
		return "AdminPage/index";
	}

	//Xử lý hiển thị danh sách sản phẩm
	@GetMapping("/listproducts")
	public String listProducts(Model model, @RequestParam("namePage") String namePage,
							   @RequestParam("p") Optional<Integer> p, HttpSession session) {
		// Tổng số product có trong database
		long products = iProductService.getTotalElement();
		// Quy định bao nhiêu sản phẩm 1 trang để thực hiện việc phân trang
		int numberElementOfPage = 10;
		//Tính toán số trang hợp lý
		int numberPage = (int) products / numberElementOfPage;
		//Nếu nhỏ hơn hoặc bằng 1 có nghĩa là tổng số sản phẩm k vượt quá 1 trang
		if (numberPage <= 1) {
			//Nên lúc này số trang là 1
			numberPage = 1;
		}
		//Phải có session admin thì mới cho phép vào xem sửa xóa sản phẩm
		if (session.getAttribute("admin") != null) {
			//Phân trang
			Pageable pageable = PageRequest.of(p.orElse(0), numberElementOfPage);
			Page<Product> page = iProductService.findAll(pageable);
			//Tạo mảng để xử lý phân trang
			int[] numberPageArr = new int[numberPage];
			for (int i = 0; i < numberPage; i++) {
				numberPageArr[i] = i;
			}
			//Mảng để xử li chuyển trang
			model.addAttribute("numberPage", numberPageArr);
			//Hiển thị tổng số sản phẩm
			model.addAttribute("allProducts", products);
			//Để thymeleaf biết replace component
			model.addAttribute("namePage", namePage);
			//Trang chứa các sản phẩm mà người dùng yêu cầu qua param p
			model.addAttribute("listProducts", page);
			//Page hiện tại đang ở
			model.addAttribute("currentPage", p.get());
			return "AdminPage/index";
		}
		return "redirect:/login";
	}

	@GetMapping("/listorders")
	public String listOrders(Model model, @RequestParam("namePage") String namePage,
							 @RequestParam("p") Optional<Integer> p, HttpSession session){
		long orders = iOrderService.getTotalElement();
		int numberElementOfPage = 5;
		int numberPage = (int) orders / numberElementOfPage;
		if (numberPage <= 1) {
			//Nên lúc này số trang là 1
			numberPage = 1;
		}
		//Phải có session admin thì mới cho phép vào
		if (session.getAttribute("admin") != null) {
			//Phân trang
			Pageable pageable = PageRequest.of(p.orElse(0), numberElementOfPage,
					Sort.by("date").descending());
			Page<Orders> page = iOrderService.findAll(pageable);
			//Tạo mảng để xử lý phân trang
			int[] numberPageArr = new int[numberPage];
			for (int i = 0; i < numberPage; i++) {
				numberPageArr[i] = i;
			}
			//Mảng để xử li chuyển trang
			model.addAttribute("numberPage", numberPageArr);
			//Hiển thị tổng số order
			model.addAttribute("allOrders", orders);
			//Để thymeleaf biết replace component
			model.addAttribute("namePage", namePage);
			//Trang chứa các sản phẩm mà người dùng yêu cầu qua param p
			model.addAttribute("listOrders", page);
			//Page hiện tại đang ở
			model.addAttribute("currentPage", p.get());
			return "AdminPage/index";
		}
		return "redirect:/login";
	}

	@GetMapping("listorders/orderdetail")
	public String getOrderDetail(Model model, @RequestParam("namePage") String namePage,
								 @RequestParam("id") Long id, HttpSession session){
		if (session.getAttribute("admin") != null) {
			Optional<Orders> orders = iOrderService.getOrder(id);
			model.addAttribute("namePage", namePage);
			model.addAttribute("orderdetail", orders.get().getOrderDetails());
			return "AdminPage/index";
		}
		return "redirect:/login";
	}
	//Xóa product
	@GetMapping("/listproducts/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id, HttpSession session) throws IOException {
		//Xác thực là tài khoản admin thực hiện
		if (session.getAttribute("admin") != null) {
			//Tiến hành xóa product
			iProductService.deleteProduct(id);
			return "redirect:/listproducts?namePage=products&p=0";
		}
		return "redirect:/login";
	}

	@GetMapping("/listorders/check/{id}")
	public String checkOrder(@PathVariable("id") Long id, HttpSession session) throws IOException {
		//Xác thực là tài khoản admin thực hiện
		if (session.getAttribute("admin") != null) {
			//Tiến hành xóa product
			Optional<Orders> orders = iOrderService.getOrder(id);
			orders.get().setStatus(1);
			iOrderService.save(orders.get());
			return "redirect:/listorders?namePage=listorders&p=0";
		}
		return "redirect:/login";
	}

	//Show form update theo loai san pham
	@GetMapping("/listproducts/update")
	public String showUpdateProduct(@RequestParam("id") Long id, HttpSession session, Model model,
									@RequestParam("namePage") String namePage) {
		//Gán đối tượng muốn chỉnh sửa
		Optional<Product> product = iProductService.getProduct(id);
		//Hiển thị thông tin đối tượng muốn chiỉnh sửa
		model.addAttribute("product", product.get());
		//Xử lí form như việc thêm sản phẩm
		if (namePage.equals("tablet")) {
			List<String> brandList = Arrays.asList("Apple iPad", "Samsung", "Xiaomi", "OPPO");
			List<String> diskSpaceList = Arrays.asList("16GB", "32GB", "64GB", "128GB", "256GB", "512GB", "1T", "2T");
			model.addAttribute("brandList", brandList);
			model.addAttribute("diskSpaceList", diskSpaceList);
			model.addAttribute("namePage", "tabletupdate");
			return "AdminPage/index";
		}
		if (namePage.equals("laptop")) {
			List<String> brandList = Arrays.asList("MacBook", "hp", "Asus", "Dell", "Acer", "Lenovo");
			model.addAttribute("brandList", brandList);
			model.addAttribute("namePage", "laptopupdate");
			return "AdminPage/index";
		}
		if (namePage.equals("smartwatch")) {
			List<String> brandList = Arrays.asList("Apple", "Samsung", "Xiaomi");
			model.addAttribute("brandList", brandList);
			model.addAttribute("namePage", "smartwatchupdate");
			return "AdminPage/index";
		}
		if (namePage.equals("earphone")) {
			List<String> brandList = Arrays.asList("Apple", "Samsung", "SONY", "OPPO");
			model.addAttribute("brandList", brandList);
			model.addAttribute("namePage", "earphoneupdate");
			return "AdminPage/index";
		}
		return "AdminPage/index";
	}

	//Xử lí chỉnh sửa sản phẩm
	@PostMapping("/listproducts/update")
	public String updateProduct(Model model, @ModelAttribute("product") Product product, @RequestParam("photos") MultipartFile[] photos,
								@RequestParam("imageInfor") MultipartFile imageInfor, @RequestParam("id") Long id) throws IOException {
		//Thực hiện chỉnh sửa cấu hình của sản phẩm
		Configure configure = iConfigureService.updateConfigure(id, product);
		//Set configure đã vừa chỉnh sửa vào product
		product.setConfigure(configure);
		//Xử lý nếu có chỉnh sửa ảnh infor
		if (!imageInfor.isEmpty()) {
			//Trước tiên phải clean file ảnh cũ được thực hiện ở /DeleteFile
			String pathImageInforProductBE = "./src/main/resources/static/images/" + id + "/infor/";
			DeleteFile.deleteFile(pathImageInforProductBE);
			String pathImageInforProductFE = "/NienLuan/frontendProjectCt271/projectCt271Fe/public/images/" + id + "/infor/";
			DeleteFile.deleteFile(pathImageInforProductFE);
			//Set ảnh infor
			product.setImageProductInfor(imageInfor.getOriginalFilename());
			//Lưu ảnh vào thư mục
			String filenameInfor = StringUtils.cleanPath(imageInfor.getOriginalFilename());
			String uploadDirInforBE = "./src/main/resources/static/images/" + id + "/infor/";
			FileUploadUtil.saveFile(uploadDirInforBE, filenameInfor, imageInfor);
			String uploadDirInforFE = "/NienLuan/frontendProjectCt271/projectCt271Fe/public/images/" + id + "/infor/";
			FileUploadUtil.saveFile(uploadDirInforFE, filenameInfor, imageInfor);
		}
		//Chỉnh sửa thông tin sản phẩm nếu có thực hiện ở /service/ProductServiceImpl
		iProductService.updateProduct(id, product);
        iCartDetailService.updateCartDetail(id);
		//Xử lý nếu có thêm ảnh detail, phần này xử lý tương tự như việc chỉnh sửa ảnh infor
		if (photos.length > 1) {
			iProductImageService.deleteImage(product);
			String pathImageProductFE = "/NienLuan/frontendProjectCt271/projectCt271Fe/public/images/" + id + "/";
			DeleteFile.deleteFile(pathImageProductFE);
			String pathImageProductBE = "./src/main/resources/static/images/" + id + "/";
			DeleteFile.deleteFile(pathImageProductBE);
			Arrays.asList(photos).stream().forEach(photo -> {
				ProductImage productImages = new ProductImage();
				productImages.setImage(photo.getOriginalFilename());
				productImages.setProduct(product);
				iProductImageService.addImage(productImages);
				String filename = StringUtils.cleanPath(photo.getOriginalFilename());
				String uploadDirFE = "/NienLuan/frontendProjectCt271/projectCt271Fe/public/images/" + id;
				String uploadDirBE = "./src/main/resources/static/images/" + id;
				try {
					FileUploadUtil.saveFile(uploadDirFE, filename, photo);
					FileUploadUtil.saveFile(uploadDirBE, filename, photo);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		model.addAttribute("namePage", "products");
		return "redirect:/listproducts?namePage=products&p=0";
	}
}

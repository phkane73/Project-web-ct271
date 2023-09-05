package com.ct271.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ct271.FileUploadUtil;
import com.ct271.entity.Configure;
import com.ct271.entity.Product;
import com.ct271.entity.ProductImage;
import com.ct271.service.IConfigureService;
import com.ct271.service.IProductImageService;
import com.ct271.service.IProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {

	@Autowired
	private IProductService iProductService;

	@Autowired
	private IConfigureService iConfigureService;
	
	@Autowired
	private IProductImageService iProductImageService;

	@GetMapping("/product/category")
	public String showAddProduct(Model model, @RequestParam("namePage") String namePage) {
		Product product = new Product();
		model.addAttribute("product", product);
		if (namePage.equals("tablet")) {
			List<String> brandList = Arrays.asList("Apple iPad", "Samsung", "Xiaomi", "OPPO");
			List<String> diskSpaceList = Arrays.asList("16GB", "32GB", "64GB", "128GB", "256GB", "512GB", "1T", "2T");
			model.addAttribute("brandList", brandList);
			model.addAttribute("diskSpaceList", diskSpaceList);
			model.addAttribute("namePage", "tablet");
			return "AdminPage/index";
		}
		return "AdminPage/index";
	}

	@PostMapping("/product/category")
	public String addProduct(Model model, @RequestParam("namePage") String namePage,
			@ModelAttribute("product") Product product, @RequestParam("photos") MultipartFile[] photos,
			@RequestParam("imageInfor") MultipartFile imageInfor) throws IOException{
		if (namePage.equals("tablet")) {
			Configure configure = new Configure(product.getConfigure().getRam(), product.getConfigure().getScreen(),
					product.getConfigure().getOs(), product.getConfigure().getChip(),
					product.getConfigure().getDiskSpace(), product.getConfigure().getSim(),
					product.getConfigure().getFrontCamera(), product.getConfigure().getBackCamera(),
					product.getConfigure().getPin());
			iConfigureService.addConfigure(configure);
			product.setConfigure(configure);
			product.setCategoryName("tablet");
			product.setImageProductInfor(imageInfor.getOriginalFilename());
			iProductService.addProduct(product);
			String filenameInfor = StringUtils.cleanPath(imageInfor.getOriginalFilename());
			String uploadDirInfor = "./src/main/resources/static/images/" + product.getId()+"/infor";
			FileUploadUtil.saveFile(uploadDirInfor, filenameInfor, imageInfor);		
			Arrays.asList(photos).stream().forEach(photo -> {
				ProductImage productImages = new ProductImage();
				productImages.setImage(photo.getOriginalFilename());
				productImages.setProduct(product);
				iProductImageService.addImage(productImages);	
				String filename = StringUtils.cleanPath(photo.getOriginalFilename());
				String uploadDir = "./src/main/resources/static/images/" + product.getId();
				try {
					FileUploadUtil.saveFile(uploadDir, filename, photo);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			});
			return "redirect:/product/category?namePage=tablet";
		}
		return "AdminPage/index";
	}

	@GetMapping("/product/products/{id}")
	public String getProduct(@PathVariable Long id) {
		iProductService.getProduct(id);
		return "AdminPage/index";
	}
	
	@GetMapping("/listproducts")
	public String listProducts(Model model, @RequestParam("namePage") String namePage,
			@RequestParam("p") Optional<Integer> p, HttpSession session) {
		long products = iProductService.getTotalElement();	
		int	numberElementOfPage = 10;
		int numberPage = (int) products/numberElementOfPage;
		if(numberPage <= 1) {
			numberPage = 1;	
		}
		if(session.getAttribute("admin") != null) {		
			Pageable pageable = PageRequest.of(p.orElse(0), numberElementOfPage);
			Page<Product> page = iProductService.findAll(pageable);
			int[] numberPageArr = new int[numberPage];
			for(int i=0; i<numberPage; i++) {
				numberPageArr[i] = i;
			}
			model.addAttribute("numberPage", numberPageArr);
			model.addAttribute("allProducts", products);
			model.addAttribute("namePage", namePage);
			model.addAttribute("listProducts", page);
		return "AdminPage/index";
		}
		return "redirect:/login";
	}
}

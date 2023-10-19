package com.ct271.controller;
//Controller cung cấp tài nguyên cho restAPI về product

import com.ct271.entity.Cart;
import com.ct271.entity.CartDetail;
import com.ct271.entity.CartDetailKey;
import com.ct271.entity.Product;
import com.ct271.repository.ICartRepo;
import com.ct271.service.ICartDetailService;
import com.ct271.service.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("/products")
@RestController
public class ProductRestController {
    private final IProductService iProductService;

    private final ICartDetailService iCartDetailService;

    private final ICartRepo iCartRepo;


    public ProductRestController(IProductService iProductService, ICartDetailService iCartDetailService, ICartRepo iCartRepo) {
        this.iProductService = iProductService;
        this.iCartDetailService = iCartDetailService;
        this.iCartRepo = iCartRepo;
    }

    //API trả về client tất cả sản phẩm
    @GetMapping()
    public ResponseEntity<?> getAllProduct() {
        List<Product> products = iProductService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    //API trả về sản phẩm phân theo từng trang
    @GetMapping("/page")
    public ResponseEntity<?> getPage(@RequestParam("p") Optional<Integer> p,
                                     @RequestParam("category") String category,
                                     @RequestParam("brand") String brand,
                                     @RequestParam("sort") String sort) {
        //Phân trang 1 trang có 12 sản phẩm và sort nếu cần
        Pageable pageable = PageRequest.of(p.orElse(0), 12);
        Pageable pageableAsc = PageRequest.of(p.orElse(0), 12, Sort.by("price").ascending());
        Pageable pageableDesc = PageRequest.of(p.orElse(0), 12, Sort.by("price").descending());

        //Nếu param category giá trị là all
        if (category.equals("all")) {
            //Trả về tất cả sản phẩm
            if (sort.equals("asc")) {
                Page<Product> page = iProductService.findAll(pageableAsc);
                return new ResponseEntity<>(page, HttpStatus.OK);
            } else if (sort.equals("desc")) {
                Page<Product> page = iProductService.findAll(pageableDesc);
                return new ResponseEntity<>(page, HttpStatus.OK);
            }
            Page<Product> page = iProductService.findAll(pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        } else {
            //Nếu khác all thì trả về brand tương ứng
            if (!brand.equals("all")) {
                if (sort.equals("asc")) {
                    Page<Product> page = iProductService.findAllCategoryAndBrand(category, brand, pageableAsc);
                    return new ResponseEntity<>(page, HttpStatus.OK);
                } else if (sort.equals("desc")) {
                    Page<Product> page = iProductService.findAllCategoryAndBrand(category, brand, pageableDesc);
                    return new ResponseEntity<>(page, HttpStatus.OK);
                }
                Page<Product> page = iProductService.findAllCategoryAndBrand(category, brand, pageable);
                return new ResponseEntity<>(page, HttpStatus.OK);
            }
            //Ngược lại giá trị category là gì thi trả về sản phẩm theo loại đó
            if (sort.equals("asc")) {
                Page<Product> page = iProductService.findAllCategory(category, pageableAsc);
                return new ResponseEntity<>(page, HttpStatus.OK);
            } else if (sort.equals("desc")) {
                Page<Product> page = iProductService.findAllCategory(category, pageableDesc);
                return new ResponseEntity<>(page, HttpStatus.OK);
            }
            Page<Product> page = iProductService.findAllCategory(category, pageable);
            return new ResponseEntity<>(page, HttpStatus.OK);
        }
    }

    //API trả về sản phẩm theo id
    @GetMapping("/product")
    public ResponseEntity<?> getProduct(@RequestParam("id") Long id) {
        Optional<Product> product = iProductService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/addtocart")
    public ResponseEntity<?> addToCart(@RequestParam("cart_id") Long cart_id,
                                       @RequestParam("product_id") Long product_id){
        iCartDetailService.addToCart(cart_id,product_id);
        return new ResponseEntity<>("Thêm vào giỏ hàng thành công", HttpStatus.OK);
    }

    @GetMapping("/allcart")
    public ResponseEntity<?> showCart(@RequestParam("cart_id") Long cart_id){
        List<CartDetail> cartDetails = iCartDetailService.getAllCartDetail(cart_id);
        List<Optional<Product>> products = new ArrayList<>();
        for(CartDetail cartDetail : cartDetails){
            Optional<Product> product = iProductService.getProduct(cartDetail.getProduct().getId());
            products.add(product);
        }
        return new ResponseEntity<List<Optional<Product>>>(products,HttpStatus.OK);
    }

    @DeleteMapping("/deletecartdetail")
    public ResponseEntity<?> deleteCartDetail(@RequestParam("cart_id") Long cart_id,
                                              @RequestParam("product_id") Long product_id){
        CartDetailKey cartDetailKey = new CartDetailKey(product_id,cart_id);
        boolean authCartDetail = iCartDetailService.deleteCartDetail(cartDetailKey);
        if(authCartDetail) {
            return new ResponseEntity<>("Delete Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete Error", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getnumbercart")
    public ResponseEntity<?> getNumberCart(@RequestParam("cart_id") Long cart_id){
        System.out.println(cart_id);
        Integer number = iCartDetailService.count(cart_id);
        return new ResponseEntity<>(number, HttpStatus.OK);
    }

    @GetMapping("/increase")
    public ResponseEntity<?> increaseNumber(@RequestParam("cart_id") Long cart_id,
                                            @RequestParam("product_id") Long product_id){
        CartDetailKey cartDetailKey = new CartDetailKey(product_id,cart_id);
        Optional<CartDetail> cartDetail = iCartDetailService.findById(cartDetailKey);
        Optional<Product> product = iProductService.getProduct(product_id);
        Integer number = cartDetail.get().getNumberCart()+1;
        cartDetail.get().setNumberCart(number);
        cartDetail.get().setTotalPrice(new Long(product.get().getPrice())*number);
        iCartDetailService.save(cartDetail.get());
        return new ResponseEntity<>(cartDetail, HttpStatus.OK);
    }
    @GetMapping("/decrease")
    public ResponseEntity<?> decreaseNumber(@RequestParam("cart_id") Long cart_id,
                                            @RequestParam("product_id") Long product_id){
        CartDetailKey cartDetailKey = new CartDetailKey(product_id,cart_id);
        Optional<CartDetail> cartDetail = iCartDetailService.findById(cartDetailKey);
        Optional<Product> product = iProductService.getProduct(product_id);
        if(cartDetail.get().getNumberCart()>1){
            Integer number = cartDetail.get().getNumberCart()-1;
            cartDetail.get().setNumberCart(number);
            cartDetail.get().setTotalPrice(new Long(product.get().getPrice())*number);
        }
        iCartDetailService.save(cartDetail.get());
        return new ResponseEntity<>(cartDetail, HttpStatus.OK);
    }

    @GetMapping("/totalpricebycart")
    public ResponseEntity<?> totalPriceByCart(@RequestParam("cart_id") Long cart_id){
        Long total =iCartDetailService.totalPriceOfCartId(cart_id);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

}

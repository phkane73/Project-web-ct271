package com.ct271.service;

import com.ct271.entity.Cart;
import com.ct271.entity.CartDetail;
import com.ct271.entity.CartDetailKey;
import com.ct271.entity.Product;
import com.ct271.repository.ICartDetailRepo;
import com.ct271.repository.ICartRepo;
import com.ct271.repository.IProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDetailServiceImpl implements ICartDetailService{

    private final ICartDetailRepo iCartDetailRepo;

    private final ICartRepo iCartRepo;

    private  final IProductRepo iProductRepo;

    public CartDetailServiceImpl(ICartDetailRepo iCartDetailRepo, ICartRepo iCartRepo, IProductRepo iProductRepo) {
        this.iCartDetailRepo = iCartDetailRepo;
        this.iCartRepo = iCartRepo;
        this.iProductRepo = iProductRepo;
    }

    public Optional<CartDetail> findById(CartDetailKey id){
        return iCartDetailRepo.findById(id);
    }

    @Override
    public List<CartDetail> getAllCartDetail(Long cart_id) {
        return iCartDetailRepo.findAllByCartId(cart_id);
    }

    @Override
    public boolean deleteCartDetail(CartDetailKey CartDetailId) {
        iCartDetailRepo.deleteById(CartDetailId);
        return true;
    }

    @Override
    public Integer count(Long cart_id) {
        return iCartDetailRepo.countByCartId(cart_id);
    }

    @Override
    public void save(CartDetail cartDetail) {
        iCartDetailRepo.save(cartDetail);
    }

    @Override
    public Long totalPriceOfCartId(Long cart_id) {
        List<CartDetail> cartDetails = iCartDetailRepo.findAllByCartId(cart_id);
        long total = 0;
        for(CartDetail cartDetail : cartDetails){
             total = total + cartDetail.getTotalPrice();
        }
        return total;
    }

    @Override
    public void addToCart(Long cart_id, Long product_id) {
        Optional<Cart> cart = iCartRepo.findById(cart_id);
        Optional<Product> product = iProductRepo.findById(product_id);
        CartDetailKey cartDetailKey = new CartDetailKey(product_id,cart_id);
        Optional<CartDetail> cartDetailOld = iCartDetailRepo.findById(cartDetailKey);
        if(cartDetailOld.isPresent()){
            int number = cartDetailOld.get().getNumberCart()+1;
            cartDetailOld.get().setNumberCart(number);
            cartDetailOld.get().setTotalPrice(new Long(product.get().getPrice())*number);
            iCartDetailRepo.save(cartDetailOld.get());
        }else{
            CartDetail cartDetail = new CartDetail();
            cartDetail.setId(cartDetailKey);
            cartDetail.setCart(cart.get());
            cartDetail.setNumberCart(1);
            cartDetail.setProduct(product.get());
            cartDetail.setTotalPrice(new Long(product.get().getPrice()));
            iCartDetailRepo.save(cartDetail);
        }
    }
}

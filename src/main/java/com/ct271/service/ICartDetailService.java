package com.ct271.service;

import com.ct271.entity.CartDetail;
import com.ct271.entity.CartDetailKey;

import java.util.List;
import java.util.Optional;

public interface ICartDetailService {

    //thêm sản phẩm vào giỏ hàng
    public void addToCart(Long cart_id, Long product_id,String date);

    //Tìm kiếm cart qua id
    public Optional<CartDetail> findById(CartDetailKey id);

    //Danh sach san pham trong cart
    public List<CartDetail> getAllCartDetail(Long cart_id);

    //Xóa cart-detail
    public boolean deleteCartDetail(CartDetailKey CartDetailId);

    public Integer count(Long cart_id);

    public void save(CartDetail cartDetail);

    //Tính tổng tiền của tất cả sản phẩm theo cart_id
    public Long totalPriceOfCartId(Long cart_id);

    //Xóa tất cả cartDetail với productid
    public void deleteAllByProductId(Long product_id);

    public void updateCartDetail(Long product_id);
}

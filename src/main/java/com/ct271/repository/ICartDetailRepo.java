package com.ct271.repository;

import com.ct271.entity.CartDetail;
import com.ct271.entity.CartDetailKey;
import com.ct271.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartDetailRepo extends JpaRepository<CartDetail, CartDetailKey> {

    public List<CartDetail> getAllByCartIdOrderByDateDesc(Long cart_id);

    public List<CartDetail> findAllByProductId(Long product_id);

    public Integer countByCartId(Long cart_id);

    public void deleteAllByProductId(Long product_id);
}

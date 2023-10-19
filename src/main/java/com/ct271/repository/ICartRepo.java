package com.ct271.repository;

import com.ct271.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepo extends JpaRepository<Cart, Long> {
    public Cart findCartByUserId(Long userId);


}

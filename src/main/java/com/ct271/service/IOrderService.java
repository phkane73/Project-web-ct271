package com.ct271.service;

import com.ct271.entity.Orders;
import com.ct271.entity.Product;
import jakarta.persistence.criteria.Order;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IOrderService {

    //Lưu 1 order
    public Orders save(Orders order);

    //Lấy ra tất cả order của user_id
    public List<Orders> getAllOrderByUserId(Long user_id);

    //Tra ve tong so order
    public long getTotalElement();

    //Phan trang voi order
    public Page<Orders> findAll(Pageable pageable);

    //Lay ra mot order
    public Optional<Orders> getOrder(Long id);
}

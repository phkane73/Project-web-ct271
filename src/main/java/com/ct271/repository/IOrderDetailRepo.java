package com.ct271.repository;

import com.ct271.entity.OrderDetail;
import com.ct271.entity.OrderDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepo extends JpaRepository<OrderDetail, OrderDetailKey> {
}

package com.ct271.repository;

import com.ct271.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepo extends JpaRepository<Orders,Long> {

    public List<Orders> getAllByUserId(Long user_id);

    public long countByStatus(int status);
}

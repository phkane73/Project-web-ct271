package com.ct271.service;

import com.ct271.entity.Orders;
import com.ct271.entity.Product;
import com.ct271.repository.IOrderRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService{

    private final IOrderRepo iOrderRepo;

    public OrderServiceImpl(IOrderRepo iOrderRepo) {
        this.iOrderRepo = iOrderRepo;
    }


    @Override
    public Orders save(Orders order) {
        return iOrderRepo.save(order);
    }

    @Override
    public List<Orders> getAllOrderByUserId(Long user_id) {
        return iOrderRepo.getAllByUserIdOrderByDateDesc(user_id);
    }

    @Override
    public long getTotalElement() {
        return iOrderRepo.countByStatus(0);
    }

    @Override
    public Page<Orders> findAll(Pageable pageable) {
        return iOrderRepo.findAll(pageable);
    }

    @Override
    public Optional<Orders> getOrder(Long id) {
        return iOrderRepo.findById(id);
    }


}

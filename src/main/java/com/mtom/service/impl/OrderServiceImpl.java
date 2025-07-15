package com.mtom.service.impl;

import com.mtom.dto.OrderDto;
import com.mtom.entity.Customer;
import com.mtom.entity.Order;
import com.mtom.repo.CustomerRepo;
import com.mtom.repo.OrderRepo;
import com.mtom.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImpl implements OrderService
{
    @Autowired
    OrderRepo orderRepository;
    @Autowired
    CustomerRepo customerRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return null;
    }

    @Override
    public List<OrderDto> getAllOrder() {
        return List.of();
    }

    @Override
    public OrderDto getOrderById(long id) {
        return null;
    }
}

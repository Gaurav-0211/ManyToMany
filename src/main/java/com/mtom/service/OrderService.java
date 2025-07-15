package com.mtom.service;

import com.mtom.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    List<OrderDto> getAllOrder();
    OrderDto getOrderById(long id);
}

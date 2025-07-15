package com.mtom.service.impl;

import com.mtom.dto.OrderDto;
import com.mtom.entity.Customer;
import com.mtom.entity.Order;
import com.mtom.repo.CustomerRepo;
import com.mtom.repo.OrderRepo;
import com.mtom.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService
{
    @Autowired
    OrderRepo orderRepository;
    @Autowired
    CustomerRepo customerRepository;
    @Autowired
    ModelMapper mapper;

    @Override
    public OrderDto createOrder(OrderDto dto) {
        Order order = mapper.map(dto, Order.class);
        Set<Customer> customers = new HashSet<>();

        if (dto.getCustomerIds() != null) {
            dto.getCustomerIds().forEach(id -> customerRepository.findById(id).ifPresent(customers::add));
            order.setCustomers(customers);
        }

        Order saved = orderRepository.save(order);
        OrderDto result = mapper.map(saved, OrderDto.class);
        result.setCustomerIds(saved.getCustomers().stream().map(Customer::getId).collect(Collectors.toSet()));
        return result;
    }

    @Override
    public List<OrderDto> getAllOrder() {
        return orderRepository.findAll().stream().map(order -> {
            OrderDto dto = mapper.map(order, OrderDto.class);
            dto.setCustomerIds(order.getCustomers().stream().map(Customer::getId).collect(Collectors.toSet()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(long id) {
        return orderRepository.findById(id).map(order -> {
            OrderDto dto = mapper.map(order, OrderDto.class);
            dto.setCustomerIds(order.getCustomers().stream().map(Customer::getId).collect(Collectors.toSet()));
            return dto;
        }).orElse(null);
    }
}

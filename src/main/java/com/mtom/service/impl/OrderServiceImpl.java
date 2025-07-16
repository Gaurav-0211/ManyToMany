package com.mtom.service.impl;

import com.mtom.dto.OrderDto;
import com.mtom.entity.Customer;
import com.mtom.entity.Order;
import com.mtom.repo.CustomerRepo;
import com.mtom.repo.OrderRepo;
import com.mtom.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private CustomerRepo customerRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public OrderDto createOrder(OrderDto dto) {
        Order order = mapper.map(dto, Order.class);

        Set<Customer> customers = new HashSet<>();
        if (dto.getCustomerIds() != null) {
            for (Long id : dto.getCustomerIds()) {
                customerRepository.findById(id).ifPresent(customers::add);
            }
        }

        order.setCustomers(customers);
        for (Customer customer : customers) {
            customer.getOrders().add(order);
        }

        Order saved = orderRepository.save(order);
        return mapToDto(saved);
    }

    @Override
    public List<OrderDto> getAllOrder() {
        return orderRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(long id) {
        return orderRepository.findById(id)
                .map(this::mapToDto)
                .orElse(null);
    }

    private OrderDto mapToDto(Order order) {
        OrderDto dto = mapper.map(order, OrderDto.class);
        dto.setCustomerIds(order.getCustomers().stream()
                .map(Customer::getId)
                .collect(Collectors.toSet()));
        return dto;
    }
}

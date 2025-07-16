package com.mtom.service.impl;

import com.mtom.dto.CustomerDto;
import com.mtom.entity.Customer;
import com.mtom.entity.Order;
import com.mtom.repo.CustomerRepo;
import com.mtom.repo.OrderRepo;
import com.mtom.service.CustomerService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    private final ModelMapper mapper;
    private final OrderRepo orderRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo, OrderRepo orderRepo, ModelMapper mapper) {
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public CustomerDto createCustomer(CustomerDto dto) {
        Customer customer = mapper.map(dto, Customer.class);

        Set<Order> orders = new HashSet<>();

        if (dto.getOrderIds() != null) {
            for (Long id : dto.getOrderIds()) {
                orderRepo.findById(id).ifPresent(orders::add);
            }
        }
        customer.setOrders(orders);
        for (Order order : orders) {
            order.getCustomers().add(customer);
        }

        Customer saved = customerRepo.save(customer);

        CustomerDto result = mapper.map(saved, CustomerDto.class);
        result.setOrderIds(
                saved.getOrders().stream().map(Order::getId).collect(Collectors.toSet())
        );
        return result;
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        return customerRepo.findAll().stream().map(customer -> {
            CustomerDto dto = mapper.map(customer, CustomerDto.class);
            dto.setOrderIds(customer.getOrders().stream().map(Order::getId).collect(Collectors.toSet()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomer(long id) {
        return customerRepo.findById(id).map(customer -> {
            CustomerDto dto = mapper.map(customer, CustomerDto.class);
            dto.setOrderIds(customer.getOrders().stream().map(Order::getId).collect(Collectors.toSet()));
            return dto;
        }).orElse(null);
    }
}

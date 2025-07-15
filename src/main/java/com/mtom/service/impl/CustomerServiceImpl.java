package com.mtom.service.impl;

import com.mtom.dto.CustomerDto;
import com.mtom.entity.Customer;
import com.mtom.repo.CustomerRepo;
import com.mtom.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepo customerRepository;
    @Autowired
    ModelMapper mapper;


    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = mapper.map(customerDto, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        return mapper.map(savedCustomer, CustomerDto.class);
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> mapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomer(long id) {
        Optional<Customer> byId = customerRepository.findById(id);

        Customer customer;
        if(byId.isPresent()){
            customer = byId.get();
        }else{
            throw new RuntimeException("Customer not exist with this id");
        }
        CustomerDto map = mapper.map(customer, CustomerDto.class);
        return map;
    }
}

package com.mtom.service;

import com.mtom.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);
    List<CustomerDto> getAllCustomer();
    CustomerDto getCustomer(long id);
}

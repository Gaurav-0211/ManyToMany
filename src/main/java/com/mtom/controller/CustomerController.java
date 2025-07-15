package com.mtom.controller;

import com.mtom.dto.CustomerDto;
import com.mtom.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(service.createCustomer(customerDto));
    }

    @GetMapping
    public List<CustomerDto> getAll(){
        return service.getAllCustomer();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable long id){
        CustomerDto customerById = service.getCustomer(id);
        return new ResponseEntity<>(customerById, HttpStatus.OK);
    }
}

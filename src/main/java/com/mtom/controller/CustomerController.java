package com.mtom.controller;

import com.mtom.dto.CustomerDto;
import com.mtom.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService customerService) {
        this.service = customerService;
    }

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

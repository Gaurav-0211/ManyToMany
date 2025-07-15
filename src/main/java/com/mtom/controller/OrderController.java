package com.mtom.controller;

import com.mtom.dto.OrderDto;
import com.mtom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class OrderController {
    @Autowired
    OrderService service;

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(service.createOrder(orderDto));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll() {
        return ResponseEntity.ok(service.getAllOrder());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable long id) {
        OrderDto orderById = service.getOrderById(id);
        return new ResponseEntity<>(orderById, HttpStatus.OK);
    }
}

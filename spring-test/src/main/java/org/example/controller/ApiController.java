package org.example.controller;

import lombok.extern.slf4j.Slf4j;

import org.example.dto.Order;
import org.example.dto.OrderInput;
import org.example.dto.Product;
import org.example.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api")
@Slf4j
public class ApiController {

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        // Implement logic to get all users
        return ResponseEntity.ok(List.of(
                User.builder()
                        .build()
        ));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        // Implement logic to get a product by ID
        return ResponseEntity.ok(Product.builder()
                .build());
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody OrderInput orderInput) {
        // Implement logic to create a new order
        return ResponseEntity.status(201).body(
                Order.builder()
                        .build()
        );
    }
}

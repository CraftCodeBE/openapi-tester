package org.example.controller;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.Order;
import org.example.dto.OrderInput;
import org.example.dto.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ApiController {

    @GetMapping("/users")
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.ok("");
    }

    @GetMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<String> getProductById(@PathVariable int id) {
        // Implement logic to get a product by ID
        return ResponseEntity.ok("");
    }

    @PostMapping(value = "/orders", produces = "application/json")
    public ResponseEntity<String> createOrder(@PathParam("userId") int userId) {
        // Implement logic to create a new order
        return ResponseEntity.status(201).body(
                ""
        );
    }
}

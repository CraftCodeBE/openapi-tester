package org.example.controller;

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
//        return ResponseEntity.ok(List.of(
//                User.builder()
//                        .email("some@craftcode.be")
//                        .name("CraftCode")
//                        .id(1)
//                        .build()
//        ));
        return ResponseEntity.ok("[{\"id\":1,\"name\":\"CraftCode\"}]");
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

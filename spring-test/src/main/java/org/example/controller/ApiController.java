package org.example.controller;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.OrderInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ApiController {

    @GetMapping("/users")
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.ok("[{\"id\":1,\"name\":\"CraftCode\",\"email\":\"some@craftcode.be\"}]");
    }

    @GetMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<String> getProductById(@PathVariable int id) {
        // Implement logic to get a product by ID
        return ResponseEntity.ok("{\"id\":1,\"name\":\"Product A\",\"price\":10.0}");
    }

    @PostMapping(value = "/orders", consumes = "application/json")
    public ResponseEntity<String> createOrder(@PathParam("userId") int userId, @PathParam("productId") int productId, @PathParam("quantity") int quantity) {
        // Implement logic to create a new order
        return ResponseEntity.status(201).body(
                "{\"id\":1,\"userId\":1,\"productId\":1,\"quantity\":2,\"totalAmount\":20.0}"
        );
    }
}

package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.controller.ApiController;
import org.example.dto.Order;
import org.example.dto.OrderInput;
import org.example.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Path;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = ApiController.class)
@Slf4j
class DemoControllerTest extends SpringContextControllerTest{
    @Autowired
    WebApplicationContext context;

    @Value("${openapi.filepath}")
    Path openApiFile;


    @Test
    void testGetAllUsers() throws Exception {

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'name':'John Doe','email':'john.doe@example.com'}]"));
    }

    @Test
    void testGetProductById() throws Exception {

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':1,'name':'Product A','price':10.0}"));
    }

    @Test
    public void testCreateOrder() throws Exception {
        OrderInput orderInput = OrderInput.builder().userId(1).productId(1).quantity(2).build();

        mockMvc.perform(post("/orders")
                        .contentType("application/json")
                        .content("{\"userId\":1,\"productId\":1,\"quantity\":2}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{'id':1,'userId':1,'productId':1,'quantity':2,'totalAmount':20.0}"));
    }


    @Override
    protected Path openApiFilePath() {
        return openApiFile;
    }

}
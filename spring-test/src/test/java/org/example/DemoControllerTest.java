package org.example;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import lombok.extern.slf4j.Slf4j;
import org.example.controller.ApiController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Path;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = ApiController.class)
@Slf4j
class DemoControllerTest extends SpringContextControllerTest {
    @Autowired
    WebApplicationContext context;

    @Value("${openapi.filepath}")
    Path openApiFile;


    @Test
    void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/users")
                        .accept("application/json")
                        .contentType("application/json")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetProductById() throws Exception {
        mockMvc.perform(get("/products/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testCreateOrder() throws Exception {
        mockMvc.perform(post("/orders")
                        .param("userId", "1")
                        .param("productId", "1")
                        .param("quantity", "2")
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Override
    protected DefaultMockMvcBuilder mockMvcConfig(DefaultMockMvcBuilder mockMvcModifier) {
        // you can add more configuration here
        return mockMvcModifier.alwaysDo(print());
    }


    @Override
    protected OpenApiInteractionValidator.Builder validatorConfig(OpenApiInteractionValidator.Builder validatorModifier) {
//        the same is possible for the validator. For example you could override the base path
        return super.validatorConfig(validatorModifier)
//                .withBasePathOverride("/api")
                ;
    }

    @Override
    protected Path openApiFilePath() {
        return openApiFile;
    }

}
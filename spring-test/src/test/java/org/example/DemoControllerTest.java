package org.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest(classes = DemoController.class)
@Slf4j
class DemoControllerTest extends SpringContextControllerTest{
    @Autowired
    WebApplicationContext context;

    @Value("${openapi.filepath}")
    Path openApiFile;

    @Test
    void someTest() throws Exception {
        mockMvc.perform(get("/demo")
                .param("requiredParam","some required value")
                .param("optionalParam", "some optional value")
        ).andDo(print());
        assertTrue(true);
    }

    @Override
    protected Path openApiFilePath() {
        return openApiFile;
    }
}
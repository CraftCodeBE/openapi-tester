package org.example;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Path;

import static com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers.openApi;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public abstract class SpringContextControllerTest {
    protected MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    OpenApiInteractionValidator validator;

    @BeforeEach
    void setUp() throws Exception {
        validator = validationHolder(openApiFilePath());
{
        mockMvc.perform(post("/orders")
                        .param("userId", "1")
                )
                .andExpect(status().isCreated());
    }
        if (mockMvc == null) {
            mockMvc = mockMvcConfig(MockMvcBuilders.webAppContextSetup(context)
                    .alwaysExpect(openApi().isValid(validator))
            ).build();
        }
    }

    private OpenApiInteractionValidator validationHolder(Path openApiFilePath) {
        return validatorConfig(OpenApiInteractionValidator
                .createFor(openApiFilePath.toAbsolutePath().toString())
                .withBasePathOverride("/")
        ).build();
    }

    protected OpenApiInteractionValidator.Builder validatorConfig(OpenApiInteractionValidator.Builder validatorModifier) {
        return validatorModifier;
    }

    protected DefaultMockMvcBuilder mockMvcConfig(DefaultMockMvcBuilder mockMvcModifier) {
        return mockMvcModifier;
    }

    protected abstract Path openApiFilePath();

}

package org.example;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.nio.file.Path;

import static com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers.openApi;

public abstract class StandaloneControllerTest {

    protected MockMvc mockMvc;

    OpenApiInteractionValidator validator;

    @BeforeEach
    void setUp(){
        if(mockMvc == null){
            mockMvc = mockMvc(assignControllers());
        }
    }

    private MockMvc mockMvc(Object... controllers) {
        validator = validationHolder(openApiFilePath());

        return mockMvcConfig(MockMvcBuilders
                .standaloneSetup(controllers)
                .alwaysExpect(openApi().isValid(validator))
        ).build();
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

    protected StandaloneMockMvcBuilder mockMvcConfig(StandaloneMockMvcBuilder mockMvcModifier) {
        return mockMvcModifier;
    }

    protected abstract Path openApiFilePath();

    protected abstract Object[] assignControllers();

}

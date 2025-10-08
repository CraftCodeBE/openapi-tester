# 💡 Stop Guessing: Why Contract Testing is the Future of Your REST API

Tired of deploying microservices only to discover an integration bug because your backend didn't adhere to the documentation? In the world of Spring Boot and fast-paced development, **trusting your API contract is everything.**

That's why we built **OpenAPI Contract Tester**—a powerful, multi-module solution designed to turn your **OpenAPI (Swagger) document into a non-negotiable contract** during every build.

***

## The Solution: OpenAPI Contract Tester

This repository isn't just code; it's an **automated quality gate**. It leverages the power of Maven and specialized Spring testing libraries to validate every API call *before* it leaves your development environment.

### Core Technology Stack

At the heart of this solution are two key players:

* **Atlassian OpenAPI Validator:** This library reads your OpenAPI specification file and provides the validation engine.
* **Spring MockMvc Integration:** This allows us to perform **high-speed integration tests** without launching a full HTTP server, keeping your test suite fast.

### ✨ Key Features at a Glance

* **Runtime Contract Validation:** Every request and response is automatically validated against the OpenAPI definition file. If a single field, type, or status code deviates, the build fails instantly.
* **Decoupled Architecture:** The project uses a multi-module structure (`openapi-lib`, `spring-test`) to keep the core API specification separate from the implementation logic.
* **Test Simplicity:** Validation logic is centralized in an abstract base class (`SpringContextControllerTest`), keeping your actual test files clean and focused purely on scenarios.

***

## 🛠️ Get Started in 3 Steps

You can integrate this contract testing rig into your project immediately.

### Prerequisites

You'll need a stable Java environment, preferably an **LTS version like JDK 21** (or JDK 17).

> ⚠️ **A Note on JDK 25 and Lombok:** If you choose to use the latest **JDK 25**, ensure your Project Lombok dependency is updated to **v1.18.40 or higher**. Older versions will cause compiler failures due to internal JDK changes.

### Installation and Build

1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/CraftCodeBE/openapi-tester.git](https://github.com/CraftCodeBE/openapi-tester.git)
    cd openapi-tester
    ```

2.  **Run the Contract Tests:**
    ```bash
    mvn clean install
    ```
    This single command executes all tests and runs the validation logic, guaranteeing compliance with your defined OpenAPI contract.

***

## 💡 How the Validation Works

The elegance of this setup lies in its simplicity. We don't write validation logic in every test; we inject it once into the test environment.

### The Magic: MockMvc `alwaysExpect()`

The base test class (`SpringContextControllerTest`) configures `MockMvc` to apply the validation check to every single request automatically:

```Java
// Inside SpringContextControllerTest.setUp()

this.mockMvc = mockMvcConfig(MockMvcBuilders.webAppContextSetup(context)
    // This CRITICAL line applies the contract validation to all tests
    .alwaysExpect(openApi().isValid(validator)) 
).build();
```
This means your test becomes incredibly clean, only verifying the expected HTTP status and behavior:

```Java

// Example in DemoControllerTest
@Test
void testGetAllUsers() throws Exception {
    mockMvc.perform(get("/users")
                    .accept("application/json")
                    .contentType("application/json")
            )
            .andDo(print())
            .andExpect(status().isOk());
    // Validation runs silently in the background, checking the response body schema!
}
```
Stop wasting time debugging mismatched JSON contracts. Implement the OpenAPI Contract Tester and build confidence in your API.

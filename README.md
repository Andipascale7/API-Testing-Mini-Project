# API-Testing-Mini-Project

# API Testing Mini-Project

A RestAssured test automation framework for testing the AutomationExercise API endpoints.

## Project Overview

This project tests various API endpoints from [automationexercise.com](https://automationexercise.com) using RestAssured, JUnit, and POJOs for clean, maintainable test code.

### Team Members
- Joshna Joshy - [@joshnajoshy](https://www.github.com/joshnajoshy)
- Matt Lewis - [@parrais](https://www.github.com/parrais)
- Uzo Ugochukwu - [@uzougochukwu](https://www.github.com/uzougochukwu)
- Shannel Feranand - [@Shea-D-Coder](https://www.github.com/Shea-D-Coder)
- Andi Pascale - [@Andipascale7](https://www.github.com/Andipascale7)
- Ana Patricia Da Silva

## Technologies Used

- **RestAssured** - API testing framework
- **JUnit 5** - Test runner and assertions
- **Jackson** - JSON serialisation/deserialisation
- **Hamcrest** - Assertion matchers
- **Maven** - Build and dependency management

## Endpoints Tested

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/productsList` | GET | Retrieve all products |
| `/api/productsList` | POST | Invalid method test (negative scenario) |
| `/api/brandsList` | GET | Retrieve all brands |
| `/api/searchProduct` | POST | Search for products by keyword |
| `/api/createAccount` | POST | Register a new user account |
| `/api/getUserDetailByEmail` | GET | Fetch user details by email |
| `/api/deleteAccount` | DELETE | Delete a user account |

## Project Structure

```
src/
├── main/
│   └── resources/
│       └── config.properties
└── test/
    └── java/
        └── com.sparta/
            ├── createaccount.restassured/
            ├── deleteaccount.restassured/
            ├── getallbrands.restassured/
            ├── getallproducts.restassured/
            │   ├── pojos/
            │   └── utils/
            ├── getuserdetail.restassured/
            ├── postallproducts.restassured/
            └── searchproduct/
```

Each endpoint has its own package containing:
- Test classes
- POJOs for request/response mapping
- Shared utilities (Config, RequestSpecBuilder)

## How to Run Tests

### Prerequisites
- Java 11 or higher
- Maven 3.6+

### Running All Tests
```bash
mvn clean test
```

### Running Specific Test Class
```bash
mvn test -Dtest=ListAllProductsTests
```

### Running from IDE
Right-click on any test class or method and select "Run"

## How to Add New Tests

1. Create a new package under `src/test/java/com.sparta/` for your endpoint
2. Create POJO classes in a `pojos` subfolder to match the API response structure
3. Create your test class:
```java
public class YourEndpointTest {
    private static Response response;
    
    @BeforeAll
    static void setup() {
        response = RestAssured
            .given()
                .baseUri(Config.getBaseUri())
                .basePath("/api")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/yourEndpoint")
            .then()
                .extract().response();
    }
    
    @Test
    void testStatusCode() {
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }
}
```

## Technical Challenges & Solutions

### HTML-Wrapped JSON Responses
The API returns JSON wrapped in HTML tags. Solution:
```java
.expect().defaultParser(Parser.JSON)
```

### Inconsistent Response Codes
The API returns HTTP 200 for all responses but includes error codes in the response body. Tests validate both:
```java
assertThat(response.statusCode(), is(200));
assertThat(responseBody.getResponseCode(), is(405));
```

### Form Data Submission
Some endpoints require multipart form data:
```java
.contentType("multipart/form-data")
.multiPart("search_product", "tshirt")
```

### Complex Nested JSON
POJOs handle deeply nested structures:
```json
{
  "category": {
    "usertype": {
      "usertype": "Women"
    },
    "category": "Tops"
  }
}
```

## Known Issues & Defects

1. **Inconsistent HTTP Status Codes**: API returns 200 OK for error scenarios instead of appropriate error codes (e.g., 405 for unsupported methods)
2. **Irregular Product IDs**: Gaps in the ID sequence and duplicate product names
3. **Content-Type Mismatch**: API returns `text/html` instead of `application/json`
4. **Limited Documentation**: Sparse API documentation required exploratory testing

## Future Improvements

- Add more negative test scenarios (invalid data, boundary testing)
- Test for duplicate records across endpoints
- Combine related operations (e.g., create account → get details → delete)
- Implement data-driven testing with multiple test datasets
- Add API performance tests
- Generate test reports using Allure or Extent Reports

## Git Workflow

This project follows a branching strategy:
- `main` - Production-ready code
- `dev` - Development branch
- `feature/*` - Individual feature branches

## Configuration

Base URI and other settings are stored in `config.properties`:
```properties
base.uri=https://automationexercise.com
base.path=/api
```

Access via the `Config` utility class:
```java
String baseUri = Config.getBaseUri();
```



package Base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {
        @BeforeEach
        void configurar (){//Endpoint
            RestAssured.baseURI = "https://petstore.swagger.io/v2";
        }
}

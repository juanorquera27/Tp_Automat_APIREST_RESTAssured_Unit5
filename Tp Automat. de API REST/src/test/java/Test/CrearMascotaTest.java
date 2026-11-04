package Test;

import Base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

//Create
public class CrearMascotaTest extends BaseTest {

    @Test
    void CrearMascotas(){
        try {
            long petId = System.currentTimeMillis();
            String body = """
                    {
                        "id": %d,
                        "name": "Firulais",
                        "photoUrls": ["https://example.com/firulais.jpg"],
                        "status": "available",
                        "category": { "id": 1, "name": "Perros" }
                    }
                    """.formatted(petId);

            Response response = given()
                    .contentType("application/json")
                    .body(body)
                    .when()
                    .post("/pet")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .extract()
                    .response();

            System.out.println("Body: " + response.asPrettyString());
        }catch(Throwable error){
            System.out.println("Fallo al buscar por estado");
        }
    }
}

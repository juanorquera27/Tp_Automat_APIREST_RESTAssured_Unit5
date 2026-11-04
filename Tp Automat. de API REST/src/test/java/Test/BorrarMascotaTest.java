package Test;

import Base.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

//Delete
public class BorrarMascotaTest extends BaseTest {


    @Test
    void EliminarUnaMascota(){
        int id = (int) (System.nanoTime() % 800_000_000L) + 100_000;
        Map<String, Object> mascota = new LinkedHashMap<>();
        mascota.put("id", id);
        mascota.put("name", "Toby");
        mascota.put("photoUrls", List.of("https://example.com/toby.jpg"));
        mascota.put("status", "available");

        given()//Creación
                .contentType(ContentType.JSON)
                .body(mascota)
                .when()
                .post("/pet")
                .then()
                .log().all()
                .statusCode(200);

        given()//Eliminación
                .header("api_key", "special-key")
                .when()
                .delete("/pet/{petId}", id)
                .then()
                .log().all()
                .statusCode(200);

        given()//Validación
                .when()
                .get("/pet/{petId}", id)
                .then()
                .log().all()
                .statusCode(404);

    }

}

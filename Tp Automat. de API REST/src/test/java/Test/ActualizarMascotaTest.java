package Test;

import Base.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//Create
public class ActualizarMascotaTest extends BaseTest {

    @Test
    void ActualizarMascotas(){
        int id = (int) (System.nanoTime() % 800_000_000L) + 100_000;

        Map<String, Object> original = new LinkedHashMap<>();
        original.put("id", id);
        original.put("name", "Rocky");
        original.put("photoUrls", List.of("https://example.com/rocky.jpg"));
        original.put("status", "available");

        given()
                .contentType(ContentType.JSON)
                .body(original)
                .when()
                .post("/pet")
                .then()
                .log().all()
                .statusCode(200);

        Map<String, Object> actualizada = new LinkedHashMap<>();
        actualizada.put("id", id);
        actualizada.put("name", "Rocky");
        actualizada.put("photoUrls", List.of("https://example.com/rocky.jpg"));
        actualizada.put("status", "sold");

        given()
                .contentType(ContentType.JSON)
                .body(actualizada)
                .when()
                .put("/pet")
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .log().all()
                .body("status", equalTo("sold"));
    }

}

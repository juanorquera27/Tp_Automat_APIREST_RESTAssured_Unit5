package Test;

import Base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

//Update
public class BuscarMascotaTest extends BaseTest {

    @Test
    void BuscarMascotaPorID(){
        try {
            long petid = 10;
            Response response = given()
                    .pathParam("petid", petid)
                    .when()
                    .get("/pet/{petid}")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .extract()
                    .response();

            System.out.println("Body: " + response.asPrettyString());
        } catch (Throwable error){
            System.out.println("Fallo al buscar por estado");
        }

    }


}

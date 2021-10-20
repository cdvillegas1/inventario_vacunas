package ec.espe.cadavi;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AddEmpleadosTest {
    @Test
    public void testListAllFruits() {
        given()
                .when()
                .body("{\n" +
                        "    \"cedula\": \"1725473498\",\n" +
                        "    \"nombres\": \"Gissell Karina\",\n" +
                        "    \"apellidos\": \"Pacheco Ortiz\",\n" +
                        "    \"correo\": \"cdvespe@gmail.com\",\n" +
                        "    \"estado\": \"1\"\n" +
                        "}")
                .contentType("application/json")
                .post("/empleados")
                .then()
                .statusCode(201);
    }
}

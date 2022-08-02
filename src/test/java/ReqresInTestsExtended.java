import models.pojo.UserDataBodyModel;
import models.pojo.UserDataResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresInTestsExtended extends TestBase {
    @Test
    @DisplayName("Pojo Successful patch user test")
    public void patchUser() {

        UserDataBodyModel userData = new UserDataBodyModel();
        userData.setName("morpheus");
        userData.setJob("zion resident");


        UserDataResponseModel responseModel = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(userData)
                .when()
                .patch("/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(UserDataResponseModel.class);


        assertEquals("zion resident", responseModel.getJob());

    }

    @Test
    @DisplayName("Pojo unSuccessful login test")
    public void postLoginUnsuccessful() {
        UserDataBodyModel userData = new UserDataBodyModel();
        userData.setEmail("peter@klaven");

        UserDataResponseModel responseModel = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(userData)
                .when()
                .post("/api/login")
                .then()
                .statusCode(400)
                .extract().as(UserDataResponseModel.class);

        assertEquals("Missing password", responseModel.getError());


    }
}
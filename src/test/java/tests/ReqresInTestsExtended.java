package tests;

import models.lombok.UserLoginBodyModel;
import models.lombok.UserLoginResponseModel;
import models.pojo.UserDataBodyModel;
import models.pojo.UserDataResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.LoginSpecs.loginResponseSpec;
import static specs.LoginSpecs.loginSpec;
import static specs.LoginUnsuccessfulSpecs.loginUnResponse;
import static specs.LoginUnsuccessfulSpecs.loginUnSpec;
import static specs.PatchUserSpecs.patchResponse;
import static specs.PatchUserSpecs.patchSpec;

public class ReqresInTestsExtended extends TestBase {
    @Test
    @DisplayName("Pojo Successful patch user test")
    public void patchUser() {
        UserDataBodyModel userData = new UserDataBodyModel();
        userData.setName("morpheus");
        userData.setJob("zion resident");

        UserDataResponseModel responseModel = given()
                .spec(patchSpec)
                .body(userData)
                .when()
                .patch()
                .then()
                .spec(patchResponse)
                .extract().as(UserDataResponseModel.class);

        assertThat(responseModel.getJob()).isEqualTo("zion resident");
        assertThat(responseModel.getName()).isEqualTo("morpheus");


    }

    @Test
    @DisplayName("Pojo unSuccessful login test")
    public void postLoginUnsuccessful() {
        UserDataBodyModel userData = new UserDataBodyModel();
        userData.setEmail("peter@klaven");
        userData.setName("Juan");
        userData.setJob("clerk");

        UserDataResponseModel responseModel = given()
                .spec(loginUnSpec)
                .body(userData)
                .when()
                .post()
                .then()
                .spec(loginUnResponse)
                .extract().as(UserDataResponseModel.class);

        assertThat(responseModel.getError()).isEqualTo("Missing password");


    }

    @Test
    @DisplayName("Lombok login test")
    public void postLoginTest() {
        UserLoginBodyModel loginBody = new UserLoginBodyModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("pistol");

        UserLoginResponseModel loginResponse = given()
                .spec(loginSpec)
                .body(loginBody)
                .when()
                .post()
                .then()
                .spec(loginResponseSpec)
                .extract().as(UserLoginResponseModel.class);

        assertThat(loginResponse.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");


    }
}

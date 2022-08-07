package tests;

import io.restassured.http.ContentType;
import models.lombok.*;
import models.pojo.UserDataBodyModel;
import models.pojo.UserDataResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static specs.CreateUserSpecs.requestUser;
import static specs.CreateUserSpecs.responseUser;
import static specs.ListResourceSpecs.request;
import static specs.ListResourceSpecs.response;
import static specs.LoginSpecs.loginResponseSpec;
import static specs.LoginSpecs.loginSpec;
import static specs.LoginUnsuccessfulSpecs.loginUnResponse;
import static specs.LoginUnsuccessfulSpecs.loginUnSpec;
import static specs.PatchUserSpecs.patchResponse;
import static specs.PatchUserSpecs.patchSpec;

public class ReqresInTestsExtended extends TestBase {


    @Test
    public void checkEmailUsingGroovy() {
        given()
                .spec(request)
                .when()
                .get("/users?delay=3")
                .then()
                .spec(response)
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("george.bluth@reqres.in"));
    }

    @Test
    @DisplayName("Create user with lombok and specs")
    public void createUser() {
        CreateUser user = new CreateUser();
        user.setName("morpheus");
        user.setJob("leader");
        CreateUserResponse userResponse = given()
                .spec(requestUser)
                .body(user)
                .when()
                .post("/users")
                .then()
                .spec(responseUser)
                .extract().as(CreateUserResponse.class);
        assertThat(userResponse.getJob()).isEqualTo("leader");
        assertThat(userResponse.getName()).isEqualTo("morpheus");

    }

    @Test
    @DisplayName("List Resource test with specs")
    public void listResource() {

        UserData data = given()
                .spec(request)
                .when()
                .get("/unknown")
                .then()
                .spec(response)
                .extract().as(UserData.class);
        assertThat(data.getPerPage()).isEqualTo(6);
        assertThat(data.getTotal()).isEqualTo(12);


    }


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

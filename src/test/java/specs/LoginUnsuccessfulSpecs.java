package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class LoginUnsuccessfulSpecs {
    public static RequestSpecification loginUnSpec = with()
            .basePath("/api/login")
            .log().uri()
            .log().body()
            .contentType(JSON);

    public static ResponseSpecification loginUnResponse = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(LogDetail.ALL)
            .build();

}

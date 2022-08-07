package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class CreateUserSpecs {
    public static RequestSpecification requestUser = with()
            .baseUri("https://reqres.in/")
            .basePath("/api")
            .log().all()
            .contentType(JSON);
    public static ResponseSpecification responseUser = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.BODY)
            .build();

}

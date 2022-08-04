package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class PatchUserSpecs {
    public static RequestSpecification patchSpec = with()
            .basePath("/api/users/2")
            .log().uri()
            .log().body()
            .contentType(JSON);

    public static ResponseSpecification patchResponse = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.ALL)
            .build();


}

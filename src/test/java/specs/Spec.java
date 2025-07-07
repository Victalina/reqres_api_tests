package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;

public class Spec {
  public static RequestSpecification requestSpec = with()
          .filter(withCustomTemplates())
          .header("x-api-key", "reqres-free-v1")
          .log().all();

  public static ResponseSpecification responseSpecStatusCode(int statusCode) {
    ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(statusCode)
            .log(ALL)
            .build();
    return responseSpec;
  }
}

package tests;

import io.qameta.allure.*;
import models.ResourceDataModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpecStatusCode;

@Tag("all_reqres_api_tests")
@DisplayName("Получение данных ресурса")
@Owner("Victalina")
@Epic("API reqres.in")
@Story("Получение данных ресурса")
@Feature("GET запрос получения данных ресурса")
public class GetSingleResourceTests extends TestBase {


  @Test
  @DisplayName("Получение данных существующего ресурса")
  @Severity(SeverityLevel.NORMAL)
  void getSingleResourceTest() {
    int id = 2;

    ResourceDataModel response = step("Отправить GET запрос для существующего ресурса с id = " + id, () ->
            given(requestSpec)
                    .when()
                    .get("/unknown/" + id)
                    .then()
                    .spec(responseSpecStatusCode(200))
                    .extract().as(ResourceDataModel.class));

    step("Проверить ответ на запрос для существующего ресурса", () -> {
      assertThat(response.getData().getId(), is(2));
      assertThat(response.getData().getName(), is("fuchsia rose"));
      assertThat(response.getData().getYear(), is(2001));
      assertThat(response.getData().getColor(), is("#C74375"));
      assertThat(response.getData().getPantoneValue(), is("17-2031"));
    });
  }

  @Test
  @DisplayName("Получение данных несуществующего ресурса - ресурс не найден")
  @Severity(SeverityLevel.MINOR)
  void singleResourceNotFoundTest() {
    int id = 23;

    step("Отправить GET запрос для несуществующего ресурса с id = " + id, () ->
            given(requestSpec)
                    .when()
                    .get("/unknown/" + id)
                    .then()
                    .spec(responseSpecStatusCode(404)));
  }
}

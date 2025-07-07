package tests;

import io.qameta.allure.*;
import models.UserDataModel;
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
@DisplayName("Получение данных пользователя")
@Owner("Victalina")
@Epic("API reqres.in")
@Story("Получение данных пользователя")
@Feature("GET запрос получения данных пользователя")
public class GetSingleUserTests extends TestBase {


  @Test
  @DisplayName("Получение данных существующего пользователя")
  @Severity(SeverityLevel.BLOCKER)
  void getSingleUserTest() {
    int id = 2;

    UserDataModel response = step("Отправить GET запрос для существующего пользователя с id = " + id, () ->
            given(requestSpec)
                    .when()
                    .get("/users/" + id)
                    .then()
                    .spec(responseSpecStatusCode(200))
                    .extract().as(UserDataModel.class));

    step("Проверить ответ на запрос для существующего пользователя", () -> {
      assertThat(response.getData().getId(), is(id));
      assertThat(response.getData().getEmail(), is("janet.weaver@reqres.in"));
      assertThat(response.getData().getFirstName(), is("Janet"));
      assertThat(response.getData().getLastName(), is("Weaver"));
      assertThat(response.getData().getAvatar(), is("https://reqres.in/img/faces/2-image.jpg"));
    });
  }

  @Test
  @DisplayName("Получение данных несуществующего пользователя - пользователь не найден")
  @Severity(SeverityLevel.NORMAL)
  void singleUserNotFoundTest() {
    int id = 23;

    step("Отправить GET запрос для несуществующего пользователя с id = " + id, () ->
            given(requestSpec)
                    .when()
                    .get("/users/" + id)
                    .then()
                    .spec(responseSpecStatusCode(404)));
  }
}

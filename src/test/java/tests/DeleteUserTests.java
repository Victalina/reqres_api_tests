package tests;

import io.qameta.allure.*;
import models.UserBodyModel;
import models.UserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpecStatusCode;

@Tag("all_reqres_api_tests")
@DisplayName("Удаление пользователя")
@Owner("Victalina")
@Epic("API reqres.in")
@Story("Удаление пользователя")
@Feature("DELETE запрос удаления пользователя")
public class DeleteUserTests extends TestBase {

  @Test
  @DisplayName("Успешное удаление пользователя")
  @Severity(SeverityLevel.CRITICAL)
  void deleteUserTest() {

    UserBodyModel userData = new UserBodyModel("Tom", "leader");

    UserResponseModel response1 = step("Отправить POST запрос на создание нового пользователя", () ->
            given(requestSpec)
                    .body(userData)
                    .contentType(JSON)
                    .when()
                    .post("/users")
                    .then()
                    .spec(responseSpecStatusCode(201))
                    .extract().as(UserResponseModel.class));

    step("Проверить ответ на запрос создание нового пользователя", () -> {
      assertThat(response1.getName(), is(userData.getName()));
      assertThat(response1.getJob(), is(userData.getJob()));
      assertThat(response1.getId(), notNullValue());
    });

    int id = response1.getId();

    step("Отправить DELETE запрос на удаление созданного пользователя с id = " + id, () ->
            given(requestSpec)
                    .when()
                    .delete("/users" + id)
                    .then()
                    .spec(responseSpecStatusCode(204)));
  }
}

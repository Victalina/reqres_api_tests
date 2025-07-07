package tests;

import io.qameta.allure.*;
import models.ErrorResponseModel;
import models.LoginBodyModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpecStatusCode;

@Tags({
        @Tag("all_reqres_api_tests"),
        @Tag("login_reqres_api_tests")
})
@DisplayName("Логин пользователя")
@Owner("Victalina")
@Epic("API reqres.in")
@Story("Логин пользователя")
@Feature("POST запрос логин пользователя")
public class LoginTests extends TestBase {

  @Test
  @DisplayName("Успешный логин пользователя")
  @Severity(SeverityLevel.BLOCKER)
  void successfulLoginTest() {

    LoginBodyModel authData = new LoginBodyModel("eve.holt@reqres.in", "cityslicka");

    LoginResponseModel response = step("Отправить POST запрос на логин пользователя", () ->
            given(requestSpec)
                    .body(authData)
                    .contentType(JSON)
                    .when()
                    .post("/login")
                    .then()
                    .spec(responseSpecStatusCode(200))
                    .extract().as(LoginResponseModel.class));

    step("Проверить ответ успешного логина пользователя", () ->
            assertThat(response.getToken(), notNullValue()));
  }


  @Test
  @DisplayName("Неуспешный логин пользователя - отсутствует email и password")
  @Severity(SeverityLevel.CRITICAL)
  void unsuccessfulLoginMissingEmailAndPasswordTest() {
    LoginBodyModel authData = new LoginBodyModel("", "");

    ErrorResponseModel response = step("Отправить запрос POST на логин пользователя с пустыми полями email и password",
            () ->
                    given(requestSpec)
                            .body(authData)
                            .contentType(JSON)
                            .when()
                            .post("/login")
                            .then()
                            .spec(responseSpecStatusCode(400))
                            .extract().as(ErrorResponseModel.class));
    step("Проверить ответ неуспешного логина пользователя", () ->
            assertThat(response.getError(), is("Missing email or username")));
  }

  @Test
  @DisplayName("Неуспешный логин несуществующего пользователя")
  @Severity(SeverityLevel.CRITICAL)
  void userNotFoundTest() {

    LoginBodyModel authData = new LoginBodyModel("eve.holt1@reqres.in", "cityslicka");

    ErrorResponseModel response = step("Отправить POST запрос на логин пользователя с некорректным email", () ->
            given(requestSpec)
                    .body(authData)
                    .contentType(JSON)
                    .when()
                    .post("/login")
                    .then()
                    .spec(responseSpecStatusCode(400))
                    .extract().as(ErrorResponseModel.class));
    step("Проверить ответ неуспешного логина пользователя", () ->
            assertThat(response.getError(), is("user not found")));
  }

  @Test
  @DisplayName("Неуспешный логин пользователя - отсутствует password")
  @Severity(SeverityLevel.CRITICAL)
  void missingPasswordTest() {

    LoginBodyModel authData = new LoginBodyModel("eve.holt1@reqres.in", "");

    ErrorResponseModel response = step("Отправить POST запрос на логин пользователя с пустым полем password", () ->
            given(requestSpec)
                    .body(authData)
                    .contentType(JSON)
                    .when()
                    .post("/login")
                    .then()
                    .spec(responseSpecStatusCode(400))
                    .extract().as(ErrorResponseModel.class));

    step("Проверить ответ неуспешного логина пользователя", () ->
            assertThat(response.getError(), is("Missing password")));
  }

  @Test
  @DisplayName("Неуспешный логин пользователя - отсутствует email")
  @Severity(SeverityLevel.CRITICAL)
  void missingLoginTest() {

    LoginBodyModel authData = new LoginBodyModel("", "cityslicka");

    ErrorResponseModel response = step("Отправить POST запрос на логин пользователя с пустым полем email", () ->
            given(requestSpec)
                    .body(authData)
                    .contentType(JSON)
                    .when()
                    .post("/login")
                    .then()
                    .spec(responseSpecStatusCode(400))
                    .extract().as(ErrorResponseModel.class));
    step("Проверить ответ неуспешного логина пользователя", () ->
            assertThat(response.getError(), is("Missing email or username")));
  }

  @Test
  @DisplayName("Неуспешный логин пользователя - некорректное тело запроса")
  @Severity(SeverityLevel.NORMAL)
  void wrongBodyTest() {
    String authData = "%}";
    step("Отправить POST запрос на логин пользователя с некорректным телом запроса", () ->
            given(requestSpec)
                    .body(authData)
                    .contentType(JSON)
                    .when()
                    .post("/login")
                    .then()
                    .spec(responseSpecStatusCode(400)));
  }

  @Test
  @DisplayName("Неуспешный логин пользователя - отсутствует тело запроса и ContentType")
  @Severity(SeverityLevel.NORMAL)
  void missingRequestBodyTest() {
    step("Отправить POST запрос на логин пользователя без тела запроса и ContentType", () ->
            given(requestSpec)
                    .when()
                    .post("/login")
                    .then()
                    .spec(responseSpecStatusCode(415)));
  }
}

package dj.restasureddemo.better_tests_with_restassured_configuration;

import io.restassured.RestAssured;
import io.restassured.config.FailureConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.listener.ResponseValidationFailureListener;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class ChangingTheRedirectConfig {

    public static final String BASE_URL = "https://api.github.com/";

    @Test
    void maxRedirectsTest() {

        RestAssured.config = RestAssured.config()
                //.redirect(RedirectConfig.redirectConfig().followRedirects(false)); // nie podążamy za przekierowaniem, bez tego zostalibysmy przekierowani i kod statusu wynioslby 200
                        .redirect(RedirectConfig.redirectConfig().followRedirects(true).maxRedirects(1)); // max liczba przekierowań to 1 test sie uda kod bedzie 200 jezeli damy 0 kombinacja zadziała jak powyzej

        RestAssured
                .get(BASE_URL + "repos/twitter/bootstrap")
                .then()
                .statusCode(200);
    }

    @Test
    void failureConfigExample() {

        ResponseValidationFailureListener failureListener = (reqSpec, resSpec, response) ->
                System.out.printf("We have a failure, " +
                        "response status was %s and the boyd contained: %s",
                        response.getStatusCode(), response.body().asPrettyString());

        RestAssured.config = RestAssured.config()
                .failureConfig(FailureConfig.failureConfig().failureListeners(failureListener));

        RestAssured.get(BASE_URL + "users/andrejs-ps")
                .then()
                .body("some.path", Matchers.equalTo("a thing"));

    }

}

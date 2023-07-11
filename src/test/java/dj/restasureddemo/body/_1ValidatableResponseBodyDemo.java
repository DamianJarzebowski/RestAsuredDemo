package dj.restasureddemo.body;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class _1ValidatableResponseBodyDemo {

    public static final String BASE_URL = "https://api.github.com/";

    @Test
    public void matcherExample() {

        RestAssured.get(BASE_URL)
                .then()
                .body("current_user_url", equalTo(BASE_URL + "user"))
                .body(containsString("feeds_url"))
                .body(containsString("feeds_url"), containsString("current_user_url"));
    }

    @Test
    void complexBodyExample() {
        RestAssured.get(BASE_URL + "users/andrejs-ps")
                .then()
                .body("url", response -> Matchers.containsString(response.body().jsonPath().get("login")));
    }
}

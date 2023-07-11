package dj.restasureddemo.better_tests_with_restassured_configuration.x;

import dj.restasureddemo.AnotherUser;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static dj.restasureddemo.better_tests_with_restassured_configuration.x.ConfigFactory.getDefaultConfig;

public class CreatingACentralConfig {

    public static final String BASE_URL = "https://api.github.com/";

    @BeforeSuite
    void setup() {
        RestAssured.config = getDefaultConfig();
    }

    @Test
    void cleanTestWithHiddenConfig() {
        AnotherUser user = RestAssured
                .get("https://api.github.com/users/rest-assured")
                .as(AnotherUser.class);

        Assert.assertEquals(user.login, "rest-assured");
        Assert.assertEquals(user.id, 19369327);
    }
}

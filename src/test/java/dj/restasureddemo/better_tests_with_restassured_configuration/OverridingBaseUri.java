package dj.restasureddemo.better_tests_with_restassured_configuration;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class OverridingBaseUri {

    @BeforeSuite
    static void setup() {
        RestAssured.baseURI = "https://regres.in/";
        RestAssured.basePath = "api/users";
        //RestAssured.rootPath = "data";
    }

    @Test
    void testOneUsingGlobalConstants() {

        RestAssured
                .get()
                .then()
                .body("data.id[0]", Matchers.is(1));
    }

    /*

    void testTwoUsingGlobalConstants() {

        RestAssured
                .get()
                .then()
                .body("id[1]", Matchers.is(2));
    }
     */



}

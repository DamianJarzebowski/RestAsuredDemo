package dj.restasureddemo.better_tests_with_restassured_configuration;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ResponseSpecification {

    static  final String BASE_URL = "https://api.github.com/";

    @BeforeClass
    void setup() {
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @AfterClass
    void cleanup() {
        RestAssured.responseSpecification = null;
    }


    io.restassured.specification.ResponseSpecification commonResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .expectContentType(ContentType.JSON)
            .build();



    @Test
    void testWithSpecOne() {
        RestAssured
                .get(BASE_URL + "non/existing/url")
                .then()
                .spec(commonResponseSpec);
                // + more custom verifications
    }

    @Test
    void testWithSpecTwo() {
        RestAssured
                .get(BASE_URL + "non/existing/url");
                // + more custom verifications
    }

    @Test
    void testWithSpecThree() {
        RestAssured
                .get(BASE_URL + "non/existing/url")
                .then()
                .statusCode(404)
                .contentType(ContentType.JSON);
        // + more custom verifications
    }
}

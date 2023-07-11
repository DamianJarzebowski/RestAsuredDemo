package dj.restasureddemo.better_tests_with_restassured_configuration;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.Test;

import static dj.restasureddemo.better_tests_with_restassured_configuration.x.ConfigFactory.getDefaultConfig;

public class RequestSpecification {
    
    static  final String BASE_URL = "https://api.github.com/";
    
    @Test
    void tesUsingLocalRequestSpec() {
        RestAssured
                .given()
                .spec(getDefaultRequestSpec())
                .when()
                .get()
                .then()
                .statusCode(200);
    }
    
    public static io.restassured.specification.RequestSpecification getDefaultRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setConfig(getDefaultConfig())
                .build();
    }

    
    
}

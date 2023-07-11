package dj.restasureddemo.better_tests_with_restassured_configuration.x;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.FailureConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.listener.ResponseValidationFailureListener;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;

import static io.restassured.config.RedirectConfig.redirectConfig;

public class ConfigFactory {

    public static RestAssuredConfig getAnotherCustomConfig() {

        //......
        return null;

    }

    public static RestAssuredConfig getDefaultConfig() {

        ResponseValidationFailureListener failureListener = (reqSpec, resSpec, response) ->
                System.out.printf("We have a failure, " +
                                "response status was %s and the boyd contained: %s",
                        response.getStatusCode(), response.body().asPrettyString());

        return  RestAssured.config()
                .failureConfig(FailureConfig.failureConfig().failureListeners(failureListener))
                .redirect(redirectConfig().maxRedirects(1))
                .objectMapperConfig(ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory(getDefaultMapper()));
    }


    static Jackson2ObjectMapperFactory getDefaultMapper() {

        return (type, s) -> {
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return om;
        };
    }
}

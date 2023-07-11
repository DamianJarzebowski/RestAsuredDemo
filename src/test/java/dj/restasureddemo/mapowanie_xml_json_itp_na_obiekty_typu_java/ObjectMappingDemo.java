package dj.restasureddemo.mapowanie_xml_json_itp_na_obiekty_typu_java;

import com.fasterxml.jackson.databind.ObjectMapper;
import dj.restasureddemo.AnotherUser;
import dj.restasureddemo.User;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectMappingDemo {

    public static final String BASE_URL = "https://api.github.com/users/rest-assured";

    @Test
    void objectMappingTestOne() {

        User user = RestAssured.get(BASE_URL)
                .as(User.class);

        assertEquals(user.getLogin(), "rest-assured");
        assertEquals(user.getId(), 19369327);
        assertEquals(user.getPublicRepos(), 2);
    }

    @Test
    void objectMappingRelyingOnMapperType() {

        User user = RestAssured.get(BASE_URL)
                .as(User.class, ObjectMapperType.JACKSON_2);
    }

    /*

    @Test
    void objectMappingUsingSpecifiedMapper() {

        // ObjectMapper om = new ObjectMapper();

        io.restassured.mapper.ObjectMapper om = new Jackson2Mapper(new Jackson2ObjectMapperFactory()) {
            @Override
            public ObjectMapper create(Type type, String s) {
                ObjectMapper om = new ObjectMapper();
                return  om;
            }
        }

        AnotherUser user = RestAssured.get(BASE_URL)
                .as(AnotherUser.class, om);

        assertEquals(user.login, "rest-assured");
    }
    
     */
}

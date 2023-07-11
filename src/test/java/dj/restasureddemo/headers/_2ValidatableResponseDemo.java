package dj.restasureddemo.headers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.hamcrest.number.OrderingComparison;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.greaterThan;

public class _2ValidatableResponseDemo {

    public static final String BASE_URL = "https://api.github.com";

    @Test
    public void basicValidatableExample() {
        RestAssured.get(BASE_URL)
                .then()
                .assertThat() //sugar
                .statusCode(200)
                .and() //sugar
                .contentType(ContentType.JSON)
                .and().assertThat() //sugar
                .header("x-ratelimit-limit", "60");
    }

    @Test
    public void simpleHamcrestMatchers() {
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)
                .statusCode(Matchers.lessThan(300))
                .header("cache-control", Matchers.containsStringIgnoringCase("max-age=60"))
                .time(Matchers.lessThan(2L), TimeUnit.SECONDS)
                .header("etag", Matchers.notNullValue())
                .header("etag", Matchers.not(Matchers.emptyString()));
    }

    @Test
    public void complexHamcrestMatchers() {
        RestAssured.get(BASE_URL)
                .then()
                .header("x-ratelimit-limit", Integer::parseInt, Matchers.equalTo(60))
                .header("date", date -> LocalDateTime.parse(date, DateTimeFormatter.RFC_1123_DATE_TIME), OrderingComparison.comparesEqualTo(LocalDateTime.now()))
                .header("x-ratelimit-limit", response -> greaterThan(response.header("x-ratelimit-remaining")));
    }

    Map<String, String> expectedHeaders = Map.of("content-encoing",             "gzip",
                                                 "access-control-allow-origin", "*");

    @Test
    public void usingMapsToTestHeaders() {
        RestAssured.get(BASE_URL)
                .then()
                .headers(
                        "content-encoding", "gzip",
                        "access-control-allow-origin", "*",
                        "cache-control", contains("public"));
    }
}

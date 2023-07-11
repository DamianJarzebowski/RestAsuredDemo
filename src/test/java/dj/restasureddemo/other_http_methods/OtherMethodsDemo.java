package dj.restasureddemo.other_http_methods;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class OtherMethodsDemo {

    public static final String BASE_URL = "https://api.github.com/user/repos";
    public static final String TOKEN = "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";

    @Test(description = "Create a repo")
    void postTest() {
        RestAssured
                .given()
                    .header("Authorization", "token " + TOKEN)
                    .body("{\"name\": \"deleteme\"}")
                .when()
                    .post(BASE_URL)
                .then()
                    .statusCode(201);
    }

    @Test(description = "Update a repo")
    void patchTest() {
        RestAssured
                .given()
                    .header("Authorization", "token " + TOKEN)
                    .body("{\"name\": \"deleteme-patched\"}")
                .when()
                    .patch("https://api.github.com/repos/DamianJarzebowski/deleteme")
                .then()
                    .statusCode(200);
    }

    @Test(description = "Delete a repo")
    void deleteTest() {
        RestAssured
                .given()
                    .header("Authorization", "token " + TOKEN)
                .when()
                    .delete("https://api.github.com/repos/DamianJarzebowski/deleteme-patched")
                    .then()
                .statusCode(204);
    }
}

package com.javademo.api.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostsApiTests {

    private static final Logger logger = LoggerFactory.getLogger(PostsApiTests.class);


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(groups = {"regression", "api"})
    public void testGETRequestFetchesCorrectPost() {
        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", notNullValue());
    }

    @Test(groups = {"regression", "api"})
    public void testPOSTRequestCreatesNewPost() {
        String requestBody = """
            {
                "title": "foo",
                "body": "bar",
                "userId": 1
            }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("id", notNullValue());
    }

    @Test(groups = {"regression", "api"})
    public void testPUTRequestUpdatesPost() {
        String requestBody = """
            {
                "id": 1,
                "title": "updated title",
                "body": "updated body",
                "userId": 1
            }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("updated title"));
    }

    @Test(groups = {"regression", "api"})
    public void testDELETERequestDeletesPost() {
        given()
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200); // jsonplaceholder returns 200, even though nothing is actually deleted
    }
}

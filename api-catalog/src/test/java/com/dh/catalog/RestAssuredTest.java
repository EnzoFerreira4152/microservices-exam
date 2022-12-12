package com.dh.catalog;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RestAssuredTest {

    final static String BASE_URL_MOVIE = "http://localhost:8080/api/v1/movies";
    final static String BASE_URL_SERIE = "http://localhost:8080/api/v1/series";
    final static String BASE_URL_CATALOG = "http://localhost:8080/api/v1/catalogs";
    final static String GENRE_TO_TEST = "restassured";

    @Test
    @Order(0)
    void post_resources_with_http_code_200(){
        JSONObject movie = new JSONObject();
        movie.put("name", "test");
        movie.put("genre", GENRE_TO_TEST);
        movie.put("urlStream", "url");

        JSONObject serie = new JSONObject();
        JSONArray seasonsArr = new JSONArray();
        JSONObject seasonObj = new JSONObject();
        JSONArray chaptersArr = new JSONArray();
        JSONObject chapterObj = new JSONObject();

        chapterObj.put("name", "test");
        chapterObj.put("number", 1);
        chapterObj.put("urlStream", "url");
        chaptersArr.add(chapterObj);

        seasonObj.put("seasonNumber", 1);
        seasonObj.put("chapters", chaptersArr);
        seasonsArr.add(seasonObj);

        serie.put("name", "test");
        serie.put("genre", GENRE_TO_TEST);
        serie.put("seasons", seasonsArr);

        //POST MOVIE
        given().header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .body(movie.toJSONString())
                .when().post(BASE_URL_MOVIE)
                .then().statusCode(200);

        //POST SERIE
        given().header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .body(serie.toJSONString())
                .when().post(BASE_URL_SERIE)
                .then().statusCode(200);
    }

    @Test
    @Order(1)
    void get_resources_online_endpoints() throws InterruptedException {
        Response responseOnline = RestAssured.get(BASE_URL_CATALOG + "/online/" + GENRE_TO_TEST);
        System.out.println(responseOnline.getBody().asString());
    }

    @Test
    @Order(2)
    void get_resources_offline_endpoints() throws InterruptedException {
        Thread.sleep(5000);
        Response responseOffline = RestAssured.get(BASE_URL_CATALOG + "/offline/" + GENRE_TO_TEST);
        System.out.println(responseOffline.getBody().asString());
    }
}

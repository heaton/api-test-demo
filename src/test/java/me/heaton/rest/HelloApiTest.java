package me.heaton.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import me.heaton.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {Application.class})
public class HelloApiTest {

  @Value("${local.server.port}")
  private int port;

  @Before
  public void init() {
    RestAssured.port = port;
  }

  @Test
  public void get_hello() {
    expect().contentType(ContentType.TEXT).body(equalTo("Hello World!")).when().get("/services/hello");
  }

  @Test
  public void visit_by_heaton() {
    given().contentType(ContentType.JSON).body("{\"name\":\"Heaton\"}").when().get("http://localhost:12306/visit")
        .then().log().all().and().statusCode(200).and().body("permission", is("allowed"));
  }

  @Test
  public void visit_by_other() {
    given().contentType(ContentType.JSON).body("{\"name\":\"Other\"}").when().get("http://localhost:12306/visit")
        .then().statusCode(200).and().body("permission", is("deny"));
  }

}

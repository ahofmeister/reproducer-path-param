package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class FoodResourceTest {

  @Test
  public void testWithValidAnnotation() {
    Food entity = given()
            .when()
            .contentType(ContentType.JSON)
            .pathParam("userId", "12")
            .body("{" +
                    "\"name\": \"myname\"" +
                    "}")
            .post("/users/{userId}/foods")
            .then()
            .statusCode(200)
            .extract()
            .body()
            .as(Food.class);

    Assertions.assertEquals("myname", entity.getUserId());
    Assertions.assertEquals(12, entity.getId());
  }

  @Test
  public void testWithoutValidAnnotatio() {
    Food entity = given()
            .when()
            .contentType(ContentType.JSON)
            .pathParam("userId", "12")
            .body("{" +
                    "\"name\": \"myname\"" +
                    "}")
            .post("/users/{userId}/foods/working")
            .then()
            .statusCode(200)
            .extract()
            .body()
            .as(Food.class);

    Assertions.assertEquals("myname", entity.getName());
    Assertions.assertEquals("12", entity.getUserId());
    Assertions.assertNotNull(entity.getId());

  }

}
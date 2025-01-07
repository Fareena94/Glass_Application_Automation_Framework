package POJO;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

public class PostRequestBody {
   @Test
   public void testPostUsingPojo(){
       PojoClass data = new PojoClass();
       data.setName("Nitish");
       data.setLocation("Bangalore");
       data.setPhone("123456");
       String CourseArr[]={"Postman","RestAssure"};
       data.setCourses(CourseArr);

       given()
               .contentType("application/json")
               .body(data)
               .when()
               .post("http://localhost:3000/Student")
               .then()
               .statusCode(201)
               .body("name",equalTo("Nitish"))
               .body("location",equalTo("Bangalore"))
               .log().all();


   }
}

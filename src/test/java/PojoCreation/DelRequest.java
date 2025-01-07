package PojoCreation;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DelRequest {

    @Test
//    public void DelDate() {
//
//
//        Response resp = given()
//                .contentType(ContentType.JSON)
//                .when()
//                .delete("http://localhost:3000/Student");
//
//        resp.then()
//                .assertThat().statusCode(200);
//        //      .log().all();
//    }


    public void DelDate() throws Exception {
        // Fetch the JSON data
        Response response = RestAssured.get("http://localhost:3000/Student");
        String jsonString = response.asString();

        // Deserialize JSON to POJO
        ObjectMapper objectMapper = new ObjectMapper();
        ProjectPojo[] students = objectMapper.readValue(jsonString, ProjectPojo[].class);

        // Access the id field of the 10th student (index 9)
        String studentId = students[10].getProjectName();

        // Send the DELETE request
        Response resp = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete("http://localhost:3000/Student/" + studentId);

        resp.then()
                .assertThat().statusCode(200);
        // .log().all();

        System.out.println("Delete successful");
    }


}

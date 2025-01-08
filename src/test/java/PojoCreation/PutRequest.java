//package PojoCreation;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import org.testng.annotations.Test;
//
//import java.io.File;
//import java.util.Arrays;
//
//public class PutRequest {
//
//    @Test
//    public void updateData(){
//
//        // Fetch the JSON data
//        Response response = RestAssured.get("http://localhost:3000/Student");
//        String jsonString = response.asString();
//
//        // Deserialize JSON to POJO
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        ProjectPojo[] students = objectMapper.readValue(jsonString, ProjectPojo[].class);
//
////        ObjectMapper objectMapper = new ObjectMapper();
////        ProjectPojo students = objectMapper.readValue(response,ProjectPojo.class);
//
//        // Update the POJO
//        students[1].setCourse(Arrays.asList("Python", "Django"));
//
//        // Serialize POJO to JSON
//        String updatedJsonString = objectMapper.writeValueAsString(students);
//
//        // Send the updated JSON back to the server using given(), when(), then()
//        RestAssured.given()
//                .contentType(ContentType.JSON)
//                .when()
//                .put("http://localhost:3000/Student")
//                .then()
//                .statusCode(200);
//
//        System.out.println("Update successful");
//
//    }
//
//
//
//
//}

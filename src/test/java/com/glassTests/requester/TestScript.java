package com.glassTests.requester;


    import POJO.PojoClass;
    import io.restassured.RestAssured;
    import org.testng.annotations.Test;

    import java.util.List;

import static io.restassured.RestAssured.*;

public class TestScript {
        @Test
        public void getData(){
            // Base URI
            RestAssured.baseURI = "http://localhost:3000";

            // Fetch the response and deserialize JSON to List of Person objects
            List<PojoClass> persons = given()
                    .when()
                    .get("/Student")
                    .then()
                    .statusCode(200)
                    .extract().jsonPath().getList("Student[0]", PojoClass.class);

            // Iterate through the list and print details
            for (PojoClass person : persons) {
             //   System.out.println("ID: " + person.getId());
                System.out.println("Name: " + person.getName());
                System.out.println("Location: " + person.getLocation());
                System.out.println("Phone: " + person.getPhone());
                System.out.println("Courses: " + person.getCourses());
                System.out.println("-------------------------");
            }
        }
    }



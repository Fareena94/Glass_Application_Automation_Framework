package PojoCreation;

import org.testng.annotations.Test;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

    public class PostRequest {
        @Test
        public void postDataToServer() {
            //create an object to pojo class
            Random random = new Random();
            int ranNum = random.nextInt(5000);
            ProjectPojo pobj = new ProjectPojo("ABB_"+ranNum, "created", "Nitish", 0);  // for creating Json object
            Response resp = given()
                    .contentType(ContentType.JSON)
                    .body(pobj)
                    .when()
                    .post("http://localhost:3000/Student");
            resp .then()
                    .assertThat().statusCode(201);
           // .log().all();


//            String projectName = resp.jsonPath().get("projectName");
//            System.out.println(projectName);

        }

}

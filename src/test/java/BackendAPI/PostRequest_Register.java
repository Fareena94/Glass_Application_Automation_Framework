package BackendAPI;

import com.glassPages.POJO.PojoForRegisterPage;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class PostRequest_Register {


    @Test

    public void createRegister(){

        PojoForRegisterPage register = new PojoForRegisterPage("Fortnite","Fortnite@gmail.com,","Fortnite@123","ADMIN","Fortnite","Tresvista");

		Response resp = given().contentType(ContentType.JSON).body(register)
		.when().post("http://localhost:8081/api/auth/register");

		resp.then().assertThat().statusCode(200).log().all();


    }



}

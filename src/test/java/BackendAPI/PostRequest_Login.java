package BackendAPI;

import com.glassPages.POJO.PojoForLoginPage;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class PostRequest_Login {

    @Test
    public void loginPage() {

        PojoForLoginPage login = new PojoForLoginPage("ansh.internal","password123");

        given().contentType(ContentType.JSON).body(login)
                .when().post("http://localhost:8081/api/auth/login")
                .then().assertThat().statusCode(200).log().all();
    }

}

package PojoCreation;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.joda.time.DateTimeFieldType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class GetRequest {

    @Test
    public void postDataToServer() {

        Response resp = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/Student");
        resp.then()
                .assertThat().statusCode(200);
          //      .log().all();


        String Name = resp.jsonPath().get("[1].name");
            System.out.println(Name);
        String Name1 = resp.jsonPath().get("[2].name");
        System.out.println(Name1);


        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
    //    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Duration.ofSeconds(2)));

        driver.get("http://localhost:5173/login");
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(Name1);
    }
}

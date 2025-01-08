package com.glassTests.requester;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.time.Duration;
import java.util.Date;

public class BaseClass {

    public static WebDriver driver;

    public static void initialize()
    {
            WebDriverManager.edgedriver().setup();
        driver= new EdgeDriver();
        driver.manage().window().maximize();
     //   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demowebshop.tricentis.com/login");
    }
    public void captureScreenshot(String methodname)
    {
        Date d = new Date();
        String timestamp = d.toString().replace(":", "_").replace(" ", "");
        try {
            File file= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("C:\\Users\\nitish.s.wissen\\Glass-Application-Automation\\Glass_Application_Automation_Framework\\allure-results"+methodname+timestamp+".jpg"));
        }
        catch (Exception e) {
            e.getMessage();// TODO: handle exception
        }
    }
}

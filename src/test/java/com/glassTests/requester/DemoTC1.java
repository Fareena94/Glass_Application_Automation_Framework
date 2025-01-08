package com.glassTests.requester;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(com.glassTests.requester.ListenerClass.class)
public class DemoTC1 extends BaseClass {

    @Test
    public void testMethod1(){
        initialize();
        driver.findElement(By.xpath("//input[@id='Emai']")).sendKeys("abc@gmail.com");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("abc123");

    }
}

package com.glassTests.requester;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(com.glassTests.requester.ListenerClass.class)
public class DemoTC2 extends BaseClass{

    @Test
    public void testMethod2()
    {
        initialize();
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("abc@gmail.com");
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("abc123");

    }
}

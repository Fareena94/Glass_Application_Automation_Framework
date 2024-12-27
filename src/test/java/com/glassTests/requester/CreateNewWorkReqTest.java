package com.glassTests.requester;

import com.glassTests.TestUtils;

import org.apache.commons.lang.RandomStringUtils;

import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.Test;

import java.awt.*;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Random;


import static com.glassPages.Utility.SeleniumUtilities.driver;

public class CreateNewWorkReqTest extends TestUtils {

    @Test

    public void createWorkRequest() throws FileNotFoundException, AWTException {
        launchGlassApplicationAsAdmin();
        createNewWRInstance.clickOnCreateWorkRequestBtn();
        createNewWRInstance.addTitleOfWR("WorkRequest_"+RandomStringUtils.randomAlphabetic(3));
        createNewWRInstance.addPriorityToWR(getValueFromLoginDataJson("newWorkRequestData[0].lowPriority"));
        createNewWRInstance.addDeliveryDate(getValueFromLoginDataJson("newWorkRequestData[0].requestedDeliveryDate"));
        createNewWRInstance.addFileFormat(getValueFromLoginDataJson("newWorkRequestData[0].fileFormat"));
        createNewWRInstance.addDescription(RandomStringUtils.randomAlphabetic(20));
        createNewWRInstance.uploadFile();
        createNewWRInstance.addField();
        createNewWRInstance.selectField(getValueFromLoginDataJson("newWorkRequestData[0].addFields[0].lang_field"));
        createNewWRInstance.clickOnSubmitBtn();

    public void createWorkRequest() throws FileNotFoundException, InterruptedException {
        launchGlassApplicationAsAdmin();
       createNewWRInstance.clickOnCreateWorkRequestBtn();
        createNewWRInstance.addTitleOfWR("WorkRequest_"+ RandomStringUtils.randomAlphanumeric(3));
        createNewWRInstance.addPriorityToWR(getValueFromLoginDataJson("newWorkRequestData[0].lowPriority"));
        createNewWRInstance.addDeliveryDate(getValueFromLoginDataJson("newWorkRequestData[0].requestedDeliveryDate"));
        createNewWRInstance.addFileFormat(getValueFromLoginDataJson("newWorkRequestData[0].fileFormat"));
        createNewWRInstance.addDescription(RandomStringUtils.randomAlphabetic(30));
    //    createNewWRInstance.uploadFile(getValueFromLoginDataJson("newWorkRequestData[0].file"));
        Thread.sleep(4000);



    //    driver.findElement(By.xpath("//span[text()='Upload Files']")).sendKeys("C:/Users/nitish.s.wissen/Downloads/TestFile.txt");

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Upload Files']")))
//                .sendKeys("C:/Users/nitish.s.wissen/Downloads/TestFile.txt");



        createNewWRInstance.clickOnAddField();
        Thread.sleep(4000);
        createNewWRInstance.selectInformation(getValueFromLoginDataJson("newWorkRequestData[0].information"));
    //    driver.findElement(By.xpath("//select[@class='block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500']")).sendKeys("Project Code");
        Thread.sleep(4000);
        createNewWRInstance.enterInformation(getValueFromLoginDataJson("newWorkRequestData[0].enterInfo"));

    //    driver.findElement(By.xpath("//input[@class='block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500']")).sendKeys("ProjectCode");
     //   Thread.sleep(4000);

//        createNewWRInstance.clickOnCancelBtn();
//        createNewWRInstance.clickOnSaveAsDraftBtn();
        createNewWRInstance.clickOnSubmitBtn();



    }
//    @Test
//    public void stringNa(){
//        System.out.println("WorkItem"+RandomStringUtils.randomAlphabetic(4));
//    }
}

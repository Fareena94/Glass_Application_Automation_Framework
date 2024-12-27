package com.glassPages.POM.Requestor1;

import com.glassPages.Constants.FilePaths;
import com.glassPages.Utility.CommonUtility;
import com.glassPages.Utility.SeleniumUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.glassPages.Utility.ExtentSparkReport.extentLogger;

public class CreateNewWorkReq extends SeleniumUtilities {
    public Logger logger = LogManager.getLogger(CreateNewWorkReq.class);
    static CreateNewWorkReq createNewWRInstance;

    Properties requestorPageProperties;

    public CreateNewWorkReq()
    {
        requestorPageProperties = CommonUtility.readPropertyFile(FilePaths.REQUESTER_PAGE_LOCATORS);
    }

    public static CreateNewWorkReq getInstance() {
        if (createNewWRInstance == null)
            createNewWRInstance = new CreateNewWorkReq();
        return createNewWRInstance;
    }

    public void clickOnCreateWorkRequestBtn(){
        extentLogger.info("Clicking on Create New Work request button");
        clickOnElement(requestorPageProperties.getProperty("clickOnCRWBtn"));
        logger.info("clicked on create new WR button");
    }
    public void addTitleOfWR(String title){
        extentLogger.info("Adding the WR title");
        setElementText(requestorPageProperties.getProperty("addTitleForWR"),title);
        logger.info("WR title is added");
    }
    public void addPriorityToWR(String priority){
        extentLogger.info("Adding Priority");
        selectDropdownByVisibleText(requestorPageProperties.getProperty("addPriority"),priority);
        logger.info("Priority for WR added");

    }    public void addDeliveryDate(String date){
        extentLogger.info("Adding Delivery date");
        setElementText(requestorPageProperties.getProperty("requestDeliveryDate"),date);
        logger.info("Added Delivery date");
    }    public void addFileFormat(String format){
        extentLogger.info("Adding File format");
        setElementText(requestorPageProperties.getProperty("workProductFormat"),format);
        logger.info("Added File format");
    }    public void addDescription(String description){
        extentLogger.info("Adding Description");
        setElementText(requestorPageProperties.getProperty("addDescription"),description);
        logger.info("Added Description");
    }

    public void uploadFile() throws AWTException {
        extentLogger.info("Uploading the file");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        clickOnElement(requestorPageProperties.getProperty("uploadFiles"));

        Robot rb= new Robot();
        StringSelection str= new StringSelection("C:\\Users\\fareena.s.wissen\\glass\\Glass_Application_Automation_Framework\\src\\main\\resources\\TestData\\SampleFile.pdf");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str,null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        extentLogger.info("Uploaded the file");
    }
    public void addField(){
        clickOnElement(requestorPageProperties.getProperty("addFields"));
    }
    public void selectField(String field){
    selectDropdownByVisibleText(requestorPageProperties.getProperty("selectField"),field);
    }

public void clickOnCancelBtn(){
     clickOnElement(requestorPageProperties.getProperty("cancelBtn"));
}
    public void clickOnSaveAsDraftBtn(){
        clickOnElement(requestorPageProperties.getProperty("saveAsDraft"));
    }
    public void clickOnSubmitBtn(){
        clickOnElement(requestorPageProperties.getProperty("saveBtn"));
    }
}

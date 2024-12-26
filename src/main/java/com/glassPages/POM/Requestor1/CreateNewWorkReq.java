package com.glassPages.POM.Requestor1;

import com.glassPages.Constants.FilePaths;
import com.glassPages.Utility.CommonUtility;
import com.glassPages.Utility.SeleniumUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

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
    }

    public void addFileFormat(String format){
        extentLogger.info("Adding File format");
        setElementText(requestorPageProperties.getProperty("workProductFormat"),format);
        logger.info("Added File format");
    }

    public void addDescription(String description){
        extentLogger.info("Adding Description");
        setElementText(requestorPageProperties.getProperty("addDescription"),description);
        logger.info("Added Description");
    }

    public void uploadFile(String file){
        extentLogger.info("Uploading the file");
        setElementText(requestorPageProperties.getProperty("uploadFiles"),file);
        extentLogger.info("Uploaded the file");
    }


    public void clickOnAddField(){
        extentLogger.info("Adding Addition information");
        clickOnElement(requestorPageProperties.getProperty("addFields"));
        logger.info("Added Addition information");
    }


    public void selectInformation(String info){
        extentLogger.info("Selecting the Information");
        setElementText(requestorPageProperties.getProperty("selectField"),info);
        extentLogger.info("Information is selected");
    }


    public void enterInformation(String inform){
        extentLogger.info("Adding Information");
        setElementText(requestorPageProperties.getProperty("enterDepartment"),inform);
        logger.info("Added Information");
    }



    public void clickOnCancelBtn(){
        extentLogger.info("Clicking on Cancel Button");
        clickOnElement(requestorPageProperties.getProperty("cancelBtn"));
        logger.info("Clicked on Cancel Button");
    }



    public void clickOnSaveAsDraftBtn(){
        extentLogger.info("Clicking on Save As Button Button");
        clickOnElement(requestorPageProperties.getProperty("saveAsDraftBtn"));
        logger.info("Clicked on Save As Draft Button");
    }

    public void clickOnSubmitBtn(){
        extentLogger.info("Clicking on Submit Button");
        clickOnElement(requestorPageProperties.getProperty("submitBtn"));
        logger.info("Clicked on Submit Button");
    }


}

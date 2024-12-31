package com.glassPages.POM.Requestor1;

import com.glassPages.Constants.FilePaths;
import com.glassPages.Utility.CommonUtility;
import com.glassPages.Utility.SeleniumUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.Properties;

import static com.glassPages.Utility.ExtentSparkReport.extentLogger;

public class HomePage extends SeleniumUtilities {
    public Logger logger = LogManager.getLogger(HomePage.class);
    static HomePage homePageInstance;

    Properties loginProperties;

    public HomePage()
    {
        loginProperties = CommonUtility.readPropertyFile(FilePaths.REQUESTER_PAGE_LOCATORS);
    }

    public static HomePage getInstance() {
        if (homePageInstance == null)
            homePageInstance = new HomePage();
        return homePageInstance;
    }

    public void getPageTitle(){
        extentLogger.info("Getting the title of Page");
        getTextOfElement(loginProperties.getProperty("dashboardTitle"));
        logger.info("Fetching the title of the Page");


    }
    public void userLogo(){
        extentLogger.info("Clicking i=on User Profile");
        clickOnElement(loginProperties.getProperty("user"));
        extentLogger.info("Locating the sign out button");
        isElementDisplayed(loginProperties.getProperty("signOutBtn"));
        logger.info("Locating the user logo and sign out btn");
    }

    public void notifications(){
        extentLogger.info("Locating the notification logo");
        isElementDisplayed(loginProperties.getProperty("notifications"));
        logger.info("Locating the notifications logo");
    }

    public void noOfDraftRequests(){
        extentLogger.info("Getting the no of requests in Draft status");
        getTextOfElement(loginProperties.getProperty("NoOfDraftRequests"));
        logger.info("Getting the no of requests in Draft status");
    }
    public void noOfInProgressRequests(){
        extentLogger.info("Getting the no of requests in In-Progress status");
        getTextOfElement(loginProperties.getProperty("NoOfInProgressRequests"));
        logger.info("Getting the no of requests in In-Progress status");
    }
    public void noOfCompletedRequests(){
        extentLogger.info("Getting the no of requests in Completed status");
        getTextOfElement(loginProperties.getProperty("NoOfCompletedRequests"));
        logger.info("Getting the no of requests in Completed status");
    }
    public void noOfUrgentRequests(){
        extentLogger.info("Getting the no of requests in Urgent status");
        getTextOfElement(loginProperties.getProperty("NoOfUrgentRequests"));
        logger.info("Getting the no of requests in Urgent status");
    }
    public void signOut(){
        extentLogger.info("Signing out");
        clickOnElement(loginProperties.getProperty("user"));
        clickOnElement(loginProperties.getProperty("signOutBtn"));
        logger.info("Signed out");
    }
}

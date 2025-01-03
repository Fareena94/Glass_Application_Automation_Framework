package com.glassPages.POM.Requestor1;

import com.glassPages.Constants.FilePaths;
import com.glassPages.Utility.CommonUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class HomePage {
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

    public void titleOfThePage(){
        logger.info("Fetching the title of the Page");

    }
    public void userLogo(){
        logger.info("Locating the user logo");
    }

    public void getPageTitle(){
        logger.info("Getting the page title");

    }
}

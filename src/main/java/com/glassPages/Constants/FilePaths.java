package com.glassPages.Constants;

import java.io.File;

public class FilePaths {
    public static final String RESOURCE_FOLDER_PATH = "src" + File.separator +"main" + File.separator + "resources" +File.separator;
     public static final String WEB_UI_USER_CONFIG = RESOURCE_FOLDER_PATH + "CommonProperties" + File.separator + "WebUIUserConfig.properties";
    public static final String EXTENT_HTML_REPORT_PATH = System.getProperty("user.dir") + File.separator + "extentReportsOutput" + File.separator + "htmlReports" + File.separator;
    public static final String EXTENT_REPORT_PROPERTIES = RESOURCE_FOLDER_PATH + "CommonProperties" + File.separator + "ExtentReports.properties";
    public static final String SCREENSHOTS_PATH = System.getProperty("user.dir") + File.separator + "extentReportsOutput" + File.separator + "screenshots" + File.separator;

    public static final String REQUESTER_PAGE_LOCATORS = RESOURCE_FOLDER_PATH + "WebLocators" + File.separator + "RequesterPagesLocators.properties";
    public static final String REQUESTER_PAGE_DATA = RESOURCE_FOLDER_PATH + "TestData" + File.separator + "RequesterData.json";



}

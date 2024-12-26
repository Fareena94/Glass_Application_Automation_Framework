package com.glassPages.Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.glassPages.Constants.FilePaths;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class contains all the methods required for generating the better UI,
 * userfriendly and customizable html reports.
 * @author - Vanshika Chauhan
 * @version 1.0
 */
public class ExtentSparkReport {
    public static ExtentReports extent;
    public static ExtentSparkReporter spark;
    public static ExtentTest test;
    public static ExtentTest extentLogger;

    /**
     * initialise() - This method initialises ExtentSparkReporter from the provided path and generates a
     * dynamic name to the html report and sets the configuration
     */
    public static void initialise() {
        System.out.println("Initialising Extent Reports");
        extent = new ExtentReports();
        System.out.println("html Path is " + FilePaths.EXTENT_HTML_REPORT_PATH + generateDynamicName() + ".html");
        spark = new ExtentSparkReporter(FilePaths.EXTENT_HTML_REPORT_PATH + generateDynamicName() + ".html");
        setConfig();
    }

    /**
     * setConfig() - This method configures the SparkReport by adding the theme , DocumentTitle,
     * ReportName , SystemInfo etc.,
     */
    public static void setConfig() {
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("ParagonAutomation_OneOfAKind");
        spark.config().setReportName("AutomationReport_" +
                CommonUtility.readPropertyFile(FilePaths.EXTENT_REPORT_PROPERTIES).getProperty("ProjectName") + "_" +
                CommonUtility.readPropertyFile(FilePaths.EXTENT_REPORT_PROPERTIES).getProperty("TeamName"));
        spark.config().setOfflineMode(true);
        extent.attachReporter(spark);
        setSystemInfo();
    }

    /**
     * setSystemInfo()- This method set the System information like SYSTEM NAME, BROWSER NAME, OS Name,
     * BROWSER VERSION, APPLICATION URL, ENVIRONMENT etc.,
     */
    public static void setSystemInfo() {
        try {
            extent.setSystemInfo("SYSTEM NAME", InetAddress.getLocalHost().getHostName());
            extent.setSystemInfo("PLATFORM NAME", CommonUtility.readPropertyFile(FilePaths.WEB_UI_USER_CONFIG).getProperty("PLATFORM_NAME"));
            extent.setSystemInfo("PLATFORM VERSION", CommonUtility.readPropertyFile(FilePaths.WEB_UI_USER_CONFIG).getProperty("PLATFORM_VERSION"));
            extent.setSystemInfo("DEVICE NAME", CommonUtility.readPropertyFile(FilePaths.WEB_UI_USER_CONFIG).getProperty("DEVICE_NAME"));
            extent.setSystemInfo("UDID", CommonUtility.readPropertyFile(FilePaths.WEB_UI_USER_CONFIG).getProperty("UDID"));
            extent.setSystemInfo("DEVICE ENVIRONMENT", CommonUtility.readPropertyFile(FilePaths.WEB_UI_USER_CONFIG).getProperty("Environment"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * setMethod() - This method sets the method name and test result by fetching methodName from class and
     * fetching the description to create log.
     * @param m
     * @param result
     */
//    public void setMethod(Method m, Test result) {
//        test = extent.createTest(getClass().getSimpleName() + " : " + m.getName() + "()");
//        System.out.println(extent.createTest(getClass().getSimpleName()));
//        System.out.println(test);
//        extentLogger = test.createNode(result.description());
//    }

    public void setMethod(Method m, Test result) {
        test = extent.createTest(getClass().getSimpleName() + " : " + m.getName() + "( )");
        extentLogger = test.createNode(result.retryAnalyzer().getSimpleName());
    }


    public static void generateReport(ITestResult result) {

        if (result.getStatus() == ITestResult.SKIP) {
            extentLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getThrowable().getMessage(), ExtentColor.YELLOW));
        } else if (result.getStatus() == ITestResult.FAILURE) {
            String screenPath = SeleniumUtilities.getScreenshot(result);
            extentLogger.log(Status.FAIL, MarkupHelper.createCodeBlock(result.getThrowable().getMessage()));
            extentLogger.fail("PFB screenshot of failed case :  " + "",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenPath).build());
        } else if (result.getStatus() == ITestResult.SUCCESS)
            extentLogger.pass("Method : " + result.getMethod().getMethodName() + " is Pass");
        extent.flush();

    }

    /**
     * generateReport() - This method generates Extent spark report and captures screenshot of
     * failed cases along with skipped test case log messages.
     * @param result
     */
    public static void generateReportAPI(ITestResult result) {

        if (result.getStatus() == ITestResult.SKIP) {
            extentLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getThrowable().getMessage(), ExtentColor.YELLOW));
        } else if (result.getStatus() == ITestResult.FAILURE) {

            extentLogger.log(Status.FAIL, result.getThrowable().getMessage());
        } else if (result.getStatus() == ITestResult.SUCCESS)
            extentLogger.pass("Method : " + result.getMethod().getMethodName() + " is Pass");
        extent.flush();

    }

    /**
     * generateDynamicName() - This function will generate a dynamic name of current timestamp in the dd-MM-yyyy h-m-s-ms format
     * @return date
     * @author - Vanshika Chauhan
     * @version - 1.0
     */
    public static String generateDynamicName() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy h-m-s-ms");
        Date date = new Date();
        return dateFormat.format(date);
    }
}

package com.glassTests;

import com.glassPages.Constants.FilePaths;
import com.glassPages.POM.LoginPage;
import com.glassPages.POM.Requestor1.CreateNewWorkReq;
import com.glassPages.Utility.CommonUtility;
import com.glassPages.Utility.ExtentSparkReport;
import com.glassPages.Utility.JSONUtility;
import com.glassTests.requester.HomePageTest;
import lombok.SneakyThrows;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;

public class TestUtils extends ExtentSparkReport{
    public Logger logger = LogManager.getLogger(TestUtils.class);
    public static CommonUtility commonUtility;
    public static LoginPage loginPageInstance;

    public static HomePageTest homePageTestInstance;
    public static CreateNewWorkReq createNewWRInstance;

    @BeforeSuite(alwaysRun = true)
    public void extents() {
        ExtentSparkReport.initialise();
    }

    @BeforeTest(alwaysRun = true)
    public void getInstances() {
       loginPageInstance = LoginPage.getInstance();
       createNewWRInstance = CreateNewWorkReq.getInstance();

    }
    @BeforeMethod(alwaysRun = true)
    public void setMethodAndClassDetails(Method method, ITestContext result) {
        Test test = method.getAnnotation(Test.class);
        setMethod(method, test);
    }

    @SneakyThrows
    @AfterMethod(alwaysRun = true)
    public void generateTestReport(ITestResult result) {
        ExtentSparkReport.generateReport(result);
   //     loginPageInstance.closeCurrentBrowser();
    }

     public String getValueFromLoginDataJson(String regex) throws FileNotFoundException {
        return JSONUtility.getValueFromJSON(FilePaths.REQUESTER_PAGE_DATA, "$." + regex).toString();
    }
    public void initiateTheLogin_Test(String userName, String pwd) {
        loginPageInstance.addCredentials(userName,pwd);
        loginPageInstance.login();
    }
    public void launchGlassApplicationAsAdmin() throws FileNotFoundException {
        loginPageInstance.launchBrowser(getValueFromLoginDataJson("applicationURL"));
        initiateTheLogin_Test(getValueFromLoginDataJson("validCred_Admin[0].validUserName"),
                getValueFromLoginDataJson("validCred_Admin[0].validPwd"));
    }
    public void launchGlassApplicationAsInternalUser() throws FileNotFoundException {
        loginPageInstance.launchBrowser(getValueFromLoginDataJson("applicationURL"));
        initiateTheLogin_Test(getValueFromLoginDataJson("validCred_Internal[0].validUserName"),
                getValueFromLoginDataJson("validCred_Internal[0].validPwd"));
    }
    public void launchGlassApplicationAsExternalUser() throws FileNotFoundException {
        loginPageInstance.launchBrowser(getValueFromLoginDataJson("applicationURL"));
        initiateTheLogin_Test(getValueFromLoginDataJson("validCred_External[0].validUserName"),
                getValueFromLoginDataJson("validCred_Internal[0].validPwd"));
    }
    /**
     * generateRandomString() - This method generates random String based on the length range provided.
     *
     * @param length
     * @return
     * @author - Vanshika Chauhan
     * @version 1.0
     */
    public static String generateRandomString(int length) {
        System.out.println(RandomStringUtils.randomAlphabetic(length));
        return null;
    }
}

import Constants.FilePaths;
import POM.LoginPage;
import Requestor.HomePageTest;
import Utility.CommonUtility;
import Utility.JSONUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;

import java.io.FileNotFoundException;

public class TestUtils {
    public Logger logger = LogManager.getLogger(TestUtils.class);
    public static CommonUtility commonUtility;
    public static LoginPage loginPageInstance;

    public static HomePageTest homePageTestInstance;

    @BeforeTest(alwaysRun = true)
    public void getInstances() {
       loginPageInstance = LoginPage.getInstance();

    }

    public String getValueFromLoginDataJson(String regex) throws FileNotFoundException {
        return JSONUtility.getValueFromJSON(FilePaths.REQUESTER_PAGE_DATA, "$." + regex).toString();
    }
    public void initiateTheLogin_Test(String userName, String pwd) {
        loginPageInstance.addCredentials(userName,pwd);
        loginPageInstance.login();
    }
    public void launchGlassApplication() throws FileNotFoundException {
        loginPageInstance.launchBrowser(getValueFromLoginDataJson("applicationURL"));
        initiateTheLogin_Test(getValueFromLoginDataJson("validCred_Admin[0].validUserName"),
                getValueFromLoginDataJson("validCred_Admin[0].validPwd"));
    }

}

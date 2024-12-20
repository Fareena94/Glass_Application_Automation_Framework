package POM;

import Constants.FilePaths;
import Utility.CommonUtility;
import Utility.SeleniumUtilities;

import java.util.Properties;

public class LoginPage extends SeleniumUtilities {
    static LoginPage loginPageInstance;

    Properties loginProperties;

    public LoginPage()
    {
        loginProperties = CommonUtility.readPropertyFile(FilePaths.REQUESTER_PAGE_LOCATORS);
    }

    public static LoginPage getInstance() {
        if (loginPageInstance == null)
            loginPageInstance = new LoginPage();
        return loginPageInstance;
    }

    public void launchBrowser(String url) {
        initialization(url);
    }

    public void addCredentials(String userName, String pwd){
        setElementText(loginProperties.getProperty("email"),userName);
        setElementText(loginProperties.getProperty("pwd"),pwd);
    }
    public void login(){
        clickOnElement(loginProperties.getProperty("login"));
}

}

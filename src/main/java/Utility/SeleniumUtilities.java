package Utility;

import Constants.FilePaths;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;


public class SeleniumUtilities {
    static Logger log = Logger.getLogger(SeleniumUtilities.class);
    public static WebDriver driver;
    public static WebDriverWait driverWait;

    Properties userConfig;

    /**
     * Use this method to get the BrowserName set by the User in the
     * config.properties file.
     *
     * @return A string representation of the BrowserName.
     * @throws IOException
     */


    private String browserName() {
        userConfig = CommonUtility.readPropertyFile(FilePaths.WEB_UI_USER_CONFIG);
        return userConfig.getProperty("Browser");
    }

    public String getCurrentPageURL() {
        String currentURL = driver.getCurrentUrl();
        if (currentURL.isEmpty())
            throw new NullPointerException("URL of the page is empty. Please check!");
        return currentURL;
    }

    /**
     * Use this method to get the Test URL set by the User in the
     * config.properties file.
     *
     * @return A string representation of the Test URL.
     */
    private String getTestURL(String url) {
        if (userConfig == null) {
            userConfig = CommonUtility.readPropertyFile(url);
            return userConfig.getProperty("ApplicationURL");
        } else {
            return userConfig.getProperty("ApplicationURL");
        }
    }
    
    /**
     * Use this method to launch browser with the specific capabilities and
     * options as defined in the config.properties file.
     *
     * @throws IOException -Pending Add the config file property BrowserName along with
     *                    the possible values.
     *                     <p>
     *                     Pending 1. Reading the waitTime from Config file. 2. Adding
     *                     the headless chrome and headless firefox code 3.
     *                     WebDriverManager class
     */
    public void initialization(String url) {
        String browser = browserName();
        Capabilities cap = null;
        CommonUtility.writePropertyFile("OSName", System.getProperty("os.name").toLowerCase(), FilePaths.WEB_UI_USER_CONFIG);
        if (browser.equalsIgnoreCase("chrome") || browser.isEmpty() || browser.equalsIgnoreCase("Google chrome")) {
            ChromeOptions co = new ChromeOptions();
            co.addArguments("--ignore-ssl-errors=yes");
            co.addArguments("--ignore-certificate-errors");
            driver = WebDriverManager.chromedriver().capabilities(co).create();


        } else if (browser.equalsIgnoreCase("Internet Explorer") || browser.equalsIgnoreCase("IE")
                || browser.equalsIgnoreCase("InternetExplorer")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new InternetExplorerDriver();
        } else if (browser.equalsIgnoreCase("FireFox") || browser.equalsIgnoreCase("FF")) {
            WebDriverManager.iedriver().setup();
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("Edge") || browser.equalsIgnoreCase("Microsoft Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("headless chrome")) {
            ChromeOptions co = new ChromeOptions();
            co.addArguments("--ignore-ssl-errors=yes");
            co.addArguments("--ignore-certificate-errors");
            co.setHeadless(true);
            driver = WebDriverManager.chromedriver().capabilities(co).create();


        }
        try {
            driver.get(url);
        } catch (Exception e) {
            log.info("Please enter the URL in WebUIUserConfig.properties file ");
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driverWait = new WebDriverWait(driver, 30);
        cap = ((RemoteWebDriver) driver).getCapabilities();
        CommonUtility.writePropertyFile("BrowserVersion", cap.getVersion(), FilePaths.WEB_UI_USER_CONFIG);
    }

    /**
     * Read the value from Property file
     *
     * @return
     */
    public WebDriver initialization() {
        String url = getTestURL(FilePaths.WEB_UI_USER_CONFIG);
        String browser = browserName();
        Capabilities cap = null;
        CommonUtility.writePropertyFile("OSName", System.getProperty("os.name").toLowerCase(), FilePaths.WEB_UI_USER_CONFIG);
        if (browser.equalsIgnoreCase("chrome") || browser.isEmpty() || browser.equalsIgnoreCase("Google chrome")) {
            ChromeOptions co = new ChromeOptions();
            co.addArguments("--ignore-ssl-errors=yes");
            co.addArguments("--ignore-certificate-errors");
            driver = WebDriverManager.chromedriver().capabilities(co).create();
        } else if (browser.equalsIgnoreCase("Internet Explorer") || browser.equalsIgnoreCase("IE")
                || browser.equalsIgnoreCase("InternetExplorer")) {
            driver = WebDriverManager.firefoxdriver().create();
        } else if (browser.equalsIgnoreCase("Edge") || browser.equalsIgnoreCase("Microsoft Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = WebDriverManager.edgedriver().create();
        } else if (browser.equalsIgnoreCase("headless chrome")) {
            ChromeOptions co = new ChromeOptions();
            co.addArguments("--ignore-ssl-errors=yes");
            co.addArguments("--ignore-certificate-errors");
            driver = WebDriverManager.chromedriver().capabilities(co).create();

        }
        try {
            driver.get(url);
        } catch (Exception e) {
            System.out.println("Please enter the URL in WebUIUserConfig.properties file ");
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driverWait = new WebDriverWait(driver, 100);
        cap = ((RemoteWebDriver) driver).getCapabilities();
     //   CommonUtility.writePropertyFile("BrowserVersion", cap.getVersion(), Filepaths.WEB_UI_USER_CONFIG);
        return driver;
    }

    public static void refreshPage() {
//        if(!url.isEmpty()||!(url == null))
//            driver.navigate().to(url);
//        else
//            driver.navigate().to(getTestURL(Filepaths.WEB_UI_USER_CONFIG));
        driver.navigate().refresh();

    }

    /**
     * Use this method to click on an element on the current page.
     *
     * @param locator The locating mechanism.The property value for the element from
     *                the respective page's property file.
     */
    public void clickOnElement(String locator) {
        WebElement ele = getElement(locator);
        if (verifyElementIsClickable(ele))
            ele.click();
    }

    public void pageRefresh() {
        driver.navigate().refresh();
//        driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
        System.out.println("Page refreshed");
    }

    /**
     * Use this method to verify whether the element is clickable
     *
     * @param element The web element of the current page
     * @return A boolean value True, if the element is clickable else False.
     */
    public boolean verifyElementIsClickable(WebElement element) {
        try {
            ExpectedConditions.elementToBeClickable(element);
            System.out.println("Element is clickable");
        } catch (Exception e) {
            System.out.println("Element is not clickable");
            return false;
        }
        return true;
    }

    /**
     * Use this method to simulate typing text into an element which may set its
     * value
     *
     * @param locator The locating mechanism.The property value for the element from
     *                the respective page's property file.
     * @param value   Character sequence to be send to the element
     */
    public void setElementText(String locator, String value) {
        getElement(locator).sendKeys(value);
    }

    public void clearTextBox(String locator) {
        getElement(locator).clear();
    }

    public String getAttribute(String locator, String attribute) {
        return getElement(locator).getAttribute(attribute);
    }

    public String getTextOfElement(String element) {
        return getElement(element).getText();
    }

    public String getAttributeValue(String locator, String attributeName) {
        return getElement(locator).getAttribute(attributeName);
    }

    public boolean isElementDisplayed(String locator) {
        try {
            if (getElement(locator).isDisplayed()) {
                System.out.println("Element " + locator + " is  displayed");
                highlightElement(getElement(locator));
            }
        } catch (NoSuchElementException | TimeoutException te) {
            System.out.println("Element " + locator + " is not displayed");
            return false;
        }
        return true;
    }

    public boolean isElementEnabled(String locator) {
        try {
            if (getElement(locator).isEnabled()) {
                System.out.println("Element " + locator + " is  Enabled");
                highlightElement(getElement(locator));
                return true;
            } else
                return false;
        } catch (Exception nse) {
            System.out.println("Elements " + locator + " is not Enabled");
            return false;
        }


    }

    public boolean isElementDisplayedByJS(String locator) {
        try {
            if (getElementByJS(locator).isDisplayed()) {
                System.out.println("Element " + locator + " is  displayed");

            }
        } catch (Exception nse) {
            System.out.println("Elements " + locator + " is not displayed");
            return false;
        }
        return true;

    }


    /**
     * Use this method to scroll into view the element on the browser window
     *
     * @param locator The locating mechanism.The property value for the element from
     *                the respective page's property file.
     * @return
     */
    public WebElement scrollToElement(String locator) {
        WebElement ele = getElement(locator);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", ele);
        if (ele.isDisplayed()) {
            System.out.println("Element is visible after scrolling down");

            return ele;
        } else {
            System.out.println("Element is not visible after scrolling down");
        }
        return ele;
    }

    /**
     * This Function Clicks on an element using Javascript
     *
     * @param locator
     */
    public void clickOnElementUsingJS(String locator) {
        WebElement ele = getElement(locator);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", ele);
    }

    private boolean verifyElementToBeClickable(WebElement ele) {
        try {
            ExpectedConditions.elementToBeClickable(ele);
        } catch (Exception e) {
            System.out.println(ele + " is not clickable");
            return false;
        }
        return true;
    }

    /**
     * This functions double click on an element using action class double click method
     *
     * @param button
     */
    public void doubleClick(String button) {
        WebElement ele = getElement(button);
        Actions actions = new Actions(driver);
        if (verifyElementToBeClickable(ele))
            actions.doubleClick(ele).perform();
        else
            System.out.println("Element " + ele + " is not clickable");
    }

    /**
     * This method handles unexpected browser popup by clicking on left mouse key for closing the popups
     *
     * @throws AWTException
     */
    public void handelBrowserPopUp() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_LEFT);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_LEFT);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public String getAlertText() {
        if (isAlertPresent())
            return driver.switchTo().alert().getText();
        else
            return "";

    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    /**
     * Use this method to get the element by using Javascript executor
     *
     * @param locator The locating mechanism.The property value for the element from
     *                the respective page's property file.
     * @return
     */
    public WebElement getElementByJS(String locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String locatorValue = getLocatorValue(locator);
        WebElement eles = (WebElement) js.executeScript("var a = document.evaluate(\"" + locatorValue
                + "\", document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null); " +
                "if (a.snapshotLength > 0) { " +
                "   return a.snapshotItem(0);" +
                " };");

        System.out.println("In get element by JS method: " + eles);
        return eles;
    }

    /**
     * Use this method to scroll into view the element and click on that
     * element.
     *
     * @param locator The locating mechanism.The property value for the element from
     *                the respective page's property file.
     */
    public void scrollAndClick(String locator) {
        scrollToElement(locator).click();
    }

    public void alertDismiss() {
        if (isAlertPresent())
            driver.switchTo().alert().dismiss();
    }

    public void alertAccept() {
        if (isAlertPresent())
            driver.switchTo().alert().accept();
    }

    /**
     * A method to split the locator string containing locator type and locator
     * value and return the locator value.
     *
     * @param locator The locating mechanism.The property value for the element from
     *                the respective page's property file.
     * @return A string containing the locator type like xpath, css, id,
     * linktext as defined in the page's property file.
     */
    private String getLocatorType(String locator) {
        String type = splitString(locator, "]: ", 0);
        return type.substring(1).toUpperCase();
    }

    /*
     * -?- Why have created a repeated function for split string? The index is
     * not being used.
     */
    private String splitString(String locator, String regEx, int index) {
        String[] inputSpilt = locator.split(regEx);
        return inputSpilt[index];
    }

    /**
     * A method to split the locator string containing locator type and locator
     * value and return the locator value.
     *
     * @param locator The locating mechanism.The property value for the element from
     *                the respective page's property file.
     * @return A string containing the locator value
     */
    private String getLocatorValue(String locator) {
        return splitString(locator, "]: ", 1);
    }

    /**
     * Find the first WebElement using the given method. This method is affected
     * by the 'explicit wait' times in force at the time of execution. The
     * getElement(..) invocation will return a matching row, or try again
     * repeatedly until the configured timeout is reached. getElement should not
     * be used to look for non-present elements, use getElements(..) and assert
     * zero length response instead.
     *
     * @param locator The locating mechanism.The property value for the element from
     *                the respective page's property file.
     * @return The first matching element on the current page.
     */
    public WebElement getElement(String locator) {
        String locatorType = getLocatorType(locator);
        String locatorValue = getLocatorValue(locator);
        WebElement element = null;
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        switch (locatorType) {
            case "XPATH":
                element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));

                System.out.println(element);
                break;
            case "CSS":
            case "CSSSELECTOR":
                element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
                break;
            case "NAME":
                element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
                break;
            case "CLASSNAME":
                element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
                break;
            case "LINKTEXT":
                element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
                break;
            case "PARTIALLINKTEXT":
                element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
                break;
            case "TAGNAME":
                element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
                break;
            case "ID":
                element = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
                break;
            default:
                throw new InvalidArgumentException("Locator type " + locator + "is not a valid locator");
        }

        jse.executeScript("arguments[0].style.border='cyan solid 3px' ", element);
        return element;

    }

    public void highlightElement(WebElement ele) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].style.border='cyan solid 3px' ", ele);

    }


    public List<WebElement> getElements(String locator) {
        String locatorType = getLocatorType(locator);
        String locatorValue = getLocatorValue(locator);
        List<WebElement> listOfElements = null;
        switch (locatorType) {
            case "XPATH":
                listOfElements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locatorValue)));
                break;
            case "CSS":
            case "CSSSELECTOR":
                listOfElements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(locatorValue)));
                break;
            case "NAME":
                listOfElements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(locatorValue)));
                break;
            case "CLASSNAME":
                listOfElements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(locatorValue)));
                break;
            case "LINKTEXT":
                listOfElements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(locatorValue)));
                break;
            case "PARTIALLINKTEXT":
                listOfElements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.partialLinkText(locatorValue)));
                break;
            case "TAGNAME":
                listOfElements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(locatorValue)));
                break;
            case "ID":
                listOfElements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locatorValue)));
                break;
            default:
                throw new InvalidArgumentException("Locator Type " + locator + "is not a valid locator");
        }

        return listOfElements;
    }


    public void tearDown() throws IOException {
        driver.quit();
        Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
    }

    public static String imageToBase64Coversion(String imagePath) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(imagePath));
        return Base64.getEncoder().encodeToString(fileContent);
    }

     /*
     * This method used to do right click
     */
    public void rightClick(String locator) {
        WebElement ele = getElement(locator);
        Actions actions = new Actions(driver);
        actions.contextClick(ele).perform();
    }

    public void selectDropdownByVisibleText(String locator, String Value) {
        WebElement ele = getElement(locator);
        Select obj = new Select(ele);
        obj.selectByVisibleText(Value);
    }
    public void selectDropdownByIndex(String locator, int index) {
        WebElement ele = getElement(locator);
        Select obj = new Select(ele);
        obj.selectByIndex(index);
    }

    public static String captureScreenshot(WebElement imageElement) {
        File src = imageElement.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/captchascreenshots/captcha.png";
        File dest = new File(path);
        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public int getSizeOfElements(String locator) {
        int size;
        try {
            size = getElements(locator).size();
        } catch (TimeoutException te) {
            size = 0;
        }
        return size;
    }


    public static boolean isFileDownloaded(String fileName) {
        boolean exist = false;
        String Path = System.getenv("USERPROFILE") + "\\Downloads\\";
        File dir = new File(Path);
        File[] files = dir.listFiles();
        for (File listFile : files) {
            if (listFile.getName().contains(fileName)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

//    public void uploadAFileFromWindows(String element, String directoryFolder, String fileName) {
//        String path = System.getenv("USERPROFILE") + "\\" + directoryFolder + "\\" + fileName;
//        getElement(element).sendKeys(path);
//    }

    public void uploadAFileFromWindows(String element, String path) {
        //String Path = System.getenv("USERPROFILE") + "\\" + directoryFolder + "\\" + fileName;
        getElement(element).sendKeys(path);
    }

    public List<WebElement> getAllOptions(String locator) {
        WebElement ele = getElement(locator);
        Select obj = new Select(ele);
        return obj.getOptions();
    }

    public String getFirstSelectedOption(String locator) {
        WebElement ele = getElement(locator);
        Select obj = new Select(ele);
        return obj.getFirstSelectedOption().getText();
    }


    public void implicitWait(int timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    public void explicitWait(String locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement element = getElement(locator);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void windowMaximizing() {
        driver.manage().window().maximize();
    }

    public void keysLeft() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
        } catch (AWTException e) {
            e.printStackTrace();
//        Actions builder = new Actions(driver);
//        builder.sendKeys(Keys.DELETE);
//        builder.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void keysBackspace(String locator) {
        WebElement ele = getElement(locator);
        ele.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        ele.sendKeys(Keys.BACK_SPACE);
    }

    public boolean switchToOldWindow() {
        boolean flag = false;
        try {
            Set<String> s = driver.getWindowHandles();
            Object popup[] = s.toArray();
            driver.switchTo().window(popup[0].toString());
            flag = true;
            return flag;
        } catch (Exception e) {
            return flag;
        } finally {
            if (flag) {
                System.out.println("Focus navigated to the window with title");
            } else {
                System.out.println("The Window with title: is not Selected");
            }
        }
    }

    public void switchToNewWindow() throws InterruptedException {
        Thread.sleep(4000);
        String parent = driver.getWindowHandle();
        Set<String> s = driver.getWindowHandles();
        for (String child_window : s) {
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
            }
        }
    }
}


import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;

public class Login extends TestUtils{
    public Logger logger = LogManager.getLogger(TestUtils.class);
    WebDriver driver;


    @Test
    public void login() throws FileNotFoundException {
        launchGlassApplication();
    }
}

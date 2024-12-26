package com.glassTests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;


public class Login extends TestUtils {

    @BeforeMethod(alwaysRun = true)
    public void loggerDetails() {
        extentLogger.assignAuthor("Fareena").assignCategory("Glass Login").assignDevice("Microsoft Edge");
    }

    @Test
    public void login() throws FileNotFoundException {
        extentLogger.info("Logging in to the application");
        logger.info("Launching and logging in to the application");
        launchGlassApplicationAsAdmin();

    }

}

package com.glassTests.requester;

import com.glassTests.TestUtils;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class CreateNewWorkReqTest extends TestUtils {

    @Test
    public void createWorkRequest() throws FileNotFoundException {
        launchGlassApplicationAsAdmin();
        createNewWRInstance.clickOnCreateWorkRequestBtn();

    }
}

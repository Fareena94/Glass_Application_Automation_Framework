package com.glassTests.requester;

import com.glassTests.TestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.FileNotFoundException;

public class CreateNewWorkReqTest extends TestUtils {

    @Test
    public void createWorkRequest() throws FileNotFoundException, AWTException {
        launchGlassApplicationAsAdmin();
        createNewWRInstance.clickOnCreateWorkRequestBtn();
        createNewWRInstance.addTitleOfWR("WorkRequest_"+RandomStringUtils.randomAlphabetic(3));
        createNewWRInstance.addPriorityToWR(getValueFromLoginDataJson("newWorkRequestData[0].lowPriority"));
        createNewWRInstance.addDeliveryDate(getValueFromLoginDataJson("newWorkRequestData[0].requestedDeliveryDate"));
        createNewWRInstance.addFileFormat(getValueFromLoginDataJson("newWorkRequestData[0].fileFormat"));
        createNewWRInstance.addDescription(RandomStringUtils.randomAlphabetic(20));
        createNewWRInstance.uploadFile();
        createNewWRInstance.addField();
        createNewWRInstance.selectField(getValueFromLoginDataJson("newWorkRequestData[0].addFields[0].lang_field"));
        createNewWRInstance.clickOnSubmitBtn();
    }
}

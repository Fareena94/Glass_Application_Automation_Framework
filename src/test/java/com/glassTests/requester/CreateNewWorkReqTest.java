package com.glassTests.requester;

import com.glassTests.TestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.FileNotFoundException;

public class CreateNewWorkReqTest extends TestUtils {


    @Test(testName = "Work Request Creation", description = "Verifying the work request creation with Admin access")

    public void createWorkRequestWithAdminAccess() throws Exception {
        launchGlassApplicationAsAdmin();
        createNewWRInstance.clickOnCreateWorkRequestBtn();
        createNewWRInstance.addTitleOfWR("WorkRequest_" + RandomStringUtils.randomAlphabetic(3));
        createNewWRInstance.addPriorityToWR(getValueFromLoginDataJson("newWorkRequestData[0].lowPriority"));
        createNewWRInstance.addDeliveryDate(getValueFromLoginDataJson("newWorkRequestData[0].requestedDeliveryDate"));
        createNewWRInstance.addFileFormat(getValueFromLoginDataJson("newWorkRequestData[0].fileFormat_PDF"));
        createNewWRInstance.addDescription(RandomStringUtils.randomAlphabetic(20));
        createNewWRInstance.uploadFile();
        createNewWRInstance.addField();
        createNewWRInstance.selectField(getValueFromLoginDataJson("newWorkRequestData[0].addFields[0].lang_field"));
        createNewWRInstance.clickOnSubmitBtn();

    }
    @Test(testName = "Work Request Creation", description = "Verifying the work request creation with Internal user access")

    public void createWorkRequestWithInternalAccess() throws Exception {
        launchGlassApplicationAsInternalUser();
        createNewWRInstance.clickOnCreateWorkRequestBtn();
        createNewWRInstance.addTitleOfWR("WorkRequest_" + RandomStringUtils.randomAlphabetic(3));
        createNewWRInstance.addPriorityToWR(getValueFromLoginDataJson("newWorkRequestData[0].lowPriority"));
        createNewWRInstance.addDeliveryDate(getValueFromLoginDataJson("newWorkRequestData[0].requestedDeliveryDate"));
        createNewWRInstance.addFileFormat(getValueFromLoginDataJson("newWorkRequestData[0].fileFormat_TXT"));
        createNewWRInstance.addDescription(RandomStringUtils.randomAlphabetic(20));
        createNewWRInstance.uploadFile();
        createNewWRInstance.addField();
        createNewWRInstance.selectField(getValueFromLoginDataJson("newWorkRequestData[0].addFields[0].lang_field"));
        createNewWRInstance.addFieldValue(getValueFromLoginDataJson("newWorkRequestData[0].addFields[0].lang_field_value"));
        createNewWRInstance.clickOnSubmitBtn();

    }
    @Test(testName = "Work Request Creation", description = "Verifying the work request creation with External user access")

    public void createWorkRequestWithExternalAccess() throws Exception {
        launchGlassApplicationAsExternalUser();
        createNewWRInstance.clickOnCreateWorkRequestBtn();
        createNewWRInstance.addTitleOfWR("WorkRequest_" + RandomStringUtils.randomAlphabetic(3));
        createNewWRInstance.addPriorityToWR(getValueFromLoginDataJson("newWorkRequestData[0].lowPriority"));
        createNewWRInstance.addDeliveryDate(getValueFromLoginDataJson("newWorkRequestData[0].requestedDeliveryDate"));
        createNewWRInstance.addFileFormat(getValueFromLoginDataJson("newWorkRequestData[0].fileFormat_PDF"));
        createNewWRInstance.addDescription(RandomStringUtils.randomAlphabetic(20));
        createNewWRInstance.uploadFile();
        createNewWRInstance.addField();
        createNewWRInstance.selectField(getValueFromLoginDataJson("newWorkRequestData[0].addFields[0].lang_field"));
        createNewWRInstance.addFieldValue(getValueFromLoginDataJson("newWorkRequestData[0].addFields[0].lang_field_value"));
        createNewWRInstance.clickOnSubmitBtn();

    }
}

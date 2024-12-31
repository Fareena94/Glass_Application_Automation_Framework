package com.glassTests.requester;

import com.glassTests.TestUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class HomePageTest extends TestUtils {
    @Test
    public void landingPageForRequesterLoggedInAsAdmin() throws FileNotFoundException {
        launchGlassApplicationAsAdmin();
        homePageInstance.getPageTitle();
        homePageInstance.userLogo();
        homePageInstance.notifications();
        homePageInstance.noOfDraftRequests();
        homePageInstance.noOfInProgressRequests();
        homePageInstance.noOfCompletedRequests();
        homePageInstance.noOfUrgentRequests();
    }
    @Test
    public void landingPageForRequesterLoggedInAsIntUser() throws FileNotFoundException {
        launchGlassApplicationAsInternalUser();
        homePageInstance.getPageTitle();
        homePageInstance.userLogo();
        homePageInstance.notifications();
        homePageInstance.noOfDraftRequests();
        homePageInstance.noOfInProgressRequests();
        homePageInstance.noOfCompletedRequests();
        homePageInstance.noOfUrgentRequests();
    }
    @Test
    public void landingPageForRequesterLoggedInAsExtUser() throws FileNotFoundException {
        launchGlassApplicationAsExternalUser();
        homePageInstance.getPageTitle();
        homePageInstance.userLogo();
        homePageInstance.notifications();
        homePageInstance.noOfDraftRequests();
        homePageInstance.noOfInProgressRequests();
        homePageInstance.noOfCompletedRequests();
        homePageInstance.noOfUrgentRequests();
    }
    @AfterTest
    public void signOut(){
        homePageInstance.signOut();
    }

}

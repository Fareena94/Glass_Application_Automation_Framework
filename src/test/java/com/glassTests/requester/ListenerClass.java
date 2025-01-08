package com.glassTests.requester;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class ListenerClass extends BaseClass implements ITestListener {

    @Test
    public void onTestFailure(ITestResult result) {
        System.out.println("Test is failed");
        try {
            captureScreenshot(result.getName());
        }
        catch (Exception e) {
            e.getMessage();// TODO: handle exception
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }
}

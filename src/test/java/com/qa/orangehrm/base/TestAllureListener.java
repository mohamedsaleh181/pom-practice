package com.qa.orangehrm.base;

import com.microsoft.playwright.Page;
import io.qameta.allure.Attachment;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Paths;

public class TestAllureListener implements ITestListener {

    private static Page page;

    // Static method to set the page from the test
    public static void setPage(Page testPage) {
        page = testPage;
    }

//    @Attachment(value = "Screenshot on Failure", type = "image/png")
//    public byte[] takeScreenshot(String fileName) {
//        return page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(fileName + ".png")));
//    }
//    @Override
//    public void onTestFailure(ITestResult result) {
//        // Capture screenshot on test failure
//        takeScreenshot("allure-results/screenshots/" + result.getName() );
//    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (page != null) {
            try {
                // Save the screenshot to a file (optional)
                String screenshotPath = "allure-results/screenshots/" + result.getName() + ".png";
                page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));

                // Attach the screenshot to the Allure report
                attachScreenshotToAllure(result.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Save the screenshot to a specific file path
    public void saveScreenshotToFile(String filePath) {
        try {
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Attach the screenshot to the Allure report
    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] attachScreenshotToAllure(String testName) {
        try {
            return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

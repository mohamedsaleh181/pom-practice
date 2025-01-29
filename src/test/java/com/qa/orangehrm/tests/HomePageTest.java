package com.qa.orangehrm.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.CDPSession;
import com.microsoft.playwright.Page;
import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.pages.HomePage;
import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


//@Listeners({AllureTestNg.class})
public class HomePageTest extends BaseTest {
    private final Logger log = LoggerFactory.getLogger(HomePageTest.class);
    private static final ThreadLocal<ITestResult> m_currentTestResult =
            new InheritableThreadLocal<>();


//    @BeforeMethod(description = "Test Login function.")
//    @Severity(SeverityLevel.CRITICAL)
//    @Feature("Login Feature.")
//    @Story("Valid Credential Login.")
//    public void loginTest() throws FileNotFoundException {
//        log.info("Typing the username.");
//        loginpage.enterUserName(prop.getProperty("userName"));
//        log.info("Typing the password.");
//        loginpage.enterPassword(prop.getProperty("password"));
//        log.info("Clicking on the login button.");
//        homepage = loginpage.clickonLoginButton();
////        assertThat(page).hasURL(AppConstant.HOME_PAGE_URL);
//        Assert.assertTrue(homepage.isHomePage(),"Home page is not displayed after login.");
//        takeScreenshot("screenshot from home page");
//    }

//    @AfterMethod
//    public void logout(){
//        loginpage = homepage.logout();
//    }

//    @Test
//    public void profileNameTest2(){
//        log.info("Performing login...");
//        HomePage homepage = loginUtil.performLogin(prop.getProperty("userName"), prop.getProperty("password"));
//        log.info("Navigating to home page...");
//        Assert.assertTrue(homepage.isHomePage2(), "Home page verification failed.");
//
//    }

    @Test(description = "Test Home page profile name.")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Home Page Features.")
    @Story("valid elements.")
    @Step("Entering username: {0}")
    public void profileNameTest(){
        log.info("Performing login...");
//        takeScreenshot();
//        homepage = new HomePage(page);
//        LoginUtil loginUtil = new LoginUtil(page);
        HomePage homepage = loginUtil.performLogin(prop.getProperty("userName"), prop.getProperty("password"));
        log.info("Navigating to home page...");
//        homepage = loginpage.navigateToHomePge();
//        log.info("getting the profile name.");
//        String actrualProfileName = homepage.getProfileName();
//        String actrualTitle = homepage.getProfileName();
//        log.info("assert the page title.");
//        log.info("Verifying the home page...");
//        page.waitForLoadState(LoadState.NETWORKIDLE);
//        page.waitForSelector("//p[normalize-space()='My Actions']"); // Adjust this selector to fit your app
//        page.screenshot(new Page.ScreenshotOptions()
//                .setPath(Paths.get("screenshots",".png")));
//        Allure.step("performing Login to the application.");
//        takeScreenshot();
//        takeScreenshot("Screenshot from home page");

        Assert.assertTrue(homepage.isHomePage(), "Home page verification failed.");
//        page.waitForTimeout(1000);
//        log.info("Home page verification passed.");
//        if (homepage != null && homepage.isHomePage()) {
//            log.info("Successfully logged in and home page verified.");
//        } else {
//            log.error("HomePage object is null or incorrect.");
//            Assert.fail("Failed to navigate to home page after login.");
//        }
    }
    public void takeScreenshot() {
        try {
            // Create a CDP session
            CDPSession cdp = browserContext.newCDPSession(page);
            // Take a screenshot via CDP
            Gson gson = new Gson();
            Map<String, Object> params = new HashMap<>();
            params.put("format", "png");
            params.put("captureBeyondViewport", true);
            JsonElement jsonElement = gson.toJsonTree(params);
            JsonObject parmsAsJsonObject = jsonElement.getAsJsonObject();

            JsonObject jsonObject = cdp.send("Page.captureScreenshot", parmsAsJsonObject);
            Map<String, JsonElement> result = jsonObject.asMap();

            // Get the base64-encoded screenshot data
            String base64Screenshot = result.get("data").toString();
            // Remove leading & trailing quotes
            base64Screenshot = base64Screenshot.replaceAll("^\"|\"$", "");
            // Convert Base64 to byte array (to be a screenshot)
            byte[] decodedScreenshot = Base64.getDecoder().decode(base64Screenshot);
            String timestamp = (new SimpleDateFormat("dd-MM-yyyy HH-mm-ss-SSSS aaa")).format(new Date(System.currentTimeMillis()));

            // Save the screenshot to a file (for reference)
            Files.createDirectories(Paths.get("screenshots")); // Ensure directory exists
            Files.write(Paths.get("screenshots/screenshot "+ timestamp +".png"), decodedScreenshot);

            // Attach screenshot to Allure report using Allure.addAttachment
            Allure.addAttachment( "ScreenShot " + timestamp, "image/png", new ByteArrayInputStream(decodedScreenshot), "png");
            System.out.println("Screenshot saved as cdp-screenshot.png and attached to Allure report.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void takeScreenshot(String descripton) {
        try {
            // Create a CDP session
            CDPSession cdp = browserContext.newCDPSession(page);
            // Take a screenshot via CDP
            Gson gson = new Gson();
            Map<String, Object> params = new HashMap<>();
            params.put("format", "png");
            params.put("captureBeyondViewport", true);
            JsonElement jsonElement = gson.toJsonTree(params);
            JsonObject parmsAsJsonObject = jsonElement.getAsJsonObject();

            JsonObject jsonObject = cdp.send("Page.captureScreenshot", parmsAsJsonObject);
            Map<String, JsonElement> result = jsonObject.asMap();

            // Get the base64-encoded screenshot data
            String base64Screenshot = result.get("data").toString();
            // Remove leading & trailing quotes
            base64Screenshot = base64Screenshot.replaceAll("^\"|\"$", "");
            // Convert Base64 to byte array (to be a screenshot)
            byte[] decodedScreenshot = Base64.getDecoder().decode(base64Screenshot);
            String timestamp = (new SimpleDateFormat("dd-MM-yyyy HH-mm-ss-SSSS aaa")).format(new Date(System.currentTimeMillis()));

            // Save the screenshot to a file (for reference)
            Files.createDirectories(Paths.get("screenshots")); // Ensure directory exists
            Files.write(Paths.get("screenshots/screenshot "+ timestamp +".png"), decodedScreenshot);

            // Attach screenshot to Allure report using Allure.addAttachment
            Allure.addAttachment( "ScreenShot " + timestamp +" "+ descripton.trim(), "image/png", new ByteArrayInputStream(decodedScreenshot), "png");
            System.out.println("Screenshot saved as to Allure report.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @AfterMethod
    public void tackScreenshot(ITestResult result){
        if (result.getStatus() == ITestResult.SUCCESS){
            takeScreenshot("when test pass");
            log.info("screenshot taken successfully.");
        }
        if (result.getStatus() == ITestResult.FAILURE){
            takeScreenshot("when test failed");
        }
    }
//    #################################################################################

}


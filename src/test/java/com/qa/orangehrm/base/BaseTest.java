package com.qa.orangehrm.base;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.qa.orangehrm.factory.PlaywrightFactory;
import com.qa.orangehrm.pages.AdminPage;
import com.qa.orangehrm.pages.HomePage;
import com.qa.orangehrm.pages.LoginPage;
import com.qa.orangehrm.pages.MyInfoPage;
import com.qa.orangehrm.utils.LoginUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseTest implements ITestListener {
    protected PlaywrightFactory pf;
    protected Page page;
    protected Properties prop;
    protected LoginPage loginpage;
    protected HomePage homepage;
    protected LoginUtil loginUtil;
    protected MyInfoPage myInfoPage;
    protected AdminPage adminPage;
    protected BrowserContext browserContext;

    @BeforeClass
    public void setup () throws FileNotFoundException {
        pf = new PlaywrightFactory();
        prop = pf.init_prop();
        browserContext = pf.initBrowser(prop);
        page = browserContext.newPage();
        page.navigate(prop.getProperty("url").trim());
        loginpage = new LoginPage(page);
        loginUtil = new LoginUtil(page);
        TestAllureListener.setPage(page);
//        System.setProperty("allure.results.directory", "allure-results");
//        System.setProperty("allure.configuration.file", "src/test/resources/allure.properties");

//        homepage = new HomePage(page);
    }
//    @BeforeMethod
//    public void loginTest() throws FileNotFoundException {
//        loginpage.enterUserName(prop.getProperty("userName"));
//        loginpage.enterPassword(prop.getProperty("password"));
//        homepage = loginpage.clickonLoginButton();
////        assertThat(page).hasURL(AppConstant.HOME_PAGE_URL);
//        Assert.assertTrue(homepage.isHomePage(),"Home page is not displayed after login.");
//        takeScreenshot();
//    }

    @AfterClass
    public void tearDown () throws IOException {
//        Path videoPath = page.video().path();  // Get video file path
//        System.out.println("Video saved at: " + videoPath);
        page.context().browser().close();
//        Allure.addAttachment("Video", "video/webm", videoPath.toFile().toString());
//        Allure.addAttachment("Test Video", "video/mp4",
//                Files.newInputStream(videoPath), "mp4");
//        System.out.println("Video saved at: " + videoPath);
//        Files.delete(videoPath);
    }

    @AfterClass(dependsOnMethods = "tearDown")
    public void attachVideo(){
        String testName = this.getClass().getSimpleName();
        String timestamp = (new SimpleDateFormat("dd-MM-yyyy HH-mm-ss-SSSS aaa")).format(new Date(System.currentTimeMillis()));
        Path videoPath = page.video().path();
        System.out.println("Video saved at: " + videoPath);
        try {
            Allure.addAttachment("Test Video "+ testName + " " + timestamp, "video/webm",
                    Files.newInputStream(videoPath), "webm");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

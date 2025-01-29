package com.qa.orangehrm.tests;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.constants.AppConstant;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.testng.AllureTestNg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Listeners({AllureTestNg.class})
public class LoginPageTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginPageTest.class);

//    @Test(description = "Test Login Page Title.")
//    @Severity(SeverityLevel.NORMAL)
//    @Feature("Login Feature.")
//    @Story("Valid Credential Login.")
//    public void LoginPageTitleTest(){
//        String actualTitle = loginpage.getLoginPageTitle();
//        log.info("The actual title is: " + actualTitle);
//        Assert.assertEquals(actualTitle,"OrangeHRM");
//        takeScreenshot();
//    }

//    @Test(description = "Test Login Page Url.")
//    @Severity(SeverityLevel.NORMAL)
//    @Feature("Login Feature.")
//    @Story("Valid Credential Login.")
//    public void LoginPageUrlTest(){
//        String actualUrl = loginpage.getLoginPageUrl();
//        Assert.assertEquals(actualUrl,prop.getProperty("url"));
//        takeScreenshot();
//    }

    @Test(description = "Test Login function.")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Login Feature.")
    @Story("Valid Credential Login.")
    public void loginTest() throws FileNotFoundException {
        log.info("Typing the username.");
        loginpage.enterUserName(prop.getProperty("userName"));
        log.info("Typing the password.");
        loginpage.enterPassword(prop.getProperty("password"));
        log.info("Clicking on the login button.");
        homepage = loginpage.clickonLoginButton();
        assertThat(page).hasURL(AppConstant.HOME_PAGE_URL);
//        try {
//            Assert.assertTrue(homepage.isHomePage(),"Home page is not displayed after login.");
//        } catch (Exception e) {
//            takeScreenshot("screenshot from home page after login test");
//            throw new RuntimeException(e);
//        }
    }


}

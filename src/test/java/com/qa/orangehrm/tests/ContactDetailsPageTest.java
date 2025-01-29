package com.qa.orangehrm.tests;

import com.microsoft.playwright.Locator;
import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.constants.AppConstant;
import com.qa.orangehrm.pages.ContactDetailsPage;
import com.qa.orangehrm.pages.HomePage;
import com.qa.orangehrm.pages.MyInfoPage;
import com.qa.orangehrm.utils.TableUtil;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ContactDetailsPageTest extends BaseTest {
    private final static Logger log = LoggerFactory.getLogger(ContactDetailsPageTest.class);

//    @Test(description = "Verify the Contact Page Header")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Contact Page Feature")
    @Story("UI Test")
    public void VerifyPageHeader(){
        HomePage homePage = loginUtil.performLogin(prop.getProperty("userName"), prop.getProperty("password"));
        Assert.assertTrue(homePage.isHomePage(), "Home page verification failed.");
        MyInfoPage myInfoPage = homePage.navigateToMyInfoPage();
        ContactDetailsPage contactDetailsPage = myInfoPage.navigateToContactDetailsPage();
        Locator pageHeader = contactDetailsPage.getPageHeader();
        contactDetailsPage.addAttachment("C:\\Users\\Mohamed\\IdeaProjects\\POMPractice\\src\\test\\resources\\SQL Notes.pdf");
        TableUtil tableUtil = new TableUtil(page);
        assertThat(pageHeader).hasText(AppConstant.CONTACT_DETAILS_PAGE_HEADER);
    }

    @Test(description = "Verify the Contact Page Header")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Contact Page Feature")
    @Story("UI Test")
    public void verifyAddAttachment(){

    }


}

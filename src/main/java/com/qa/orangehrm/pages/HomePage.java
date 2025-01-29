package com.qa.orangehrm.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage {
    private final static Logger log = LoggerFactory.getLogger(HomePage.class);
    private final Page page;
    private final Locator actionsText;
    private final Locator myInfoTab;


    // page constructor
    public HomePage(Page page){
        this.page = page;
        actionsText = this.page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div/div[1]/div/p");
        log.info("Before select my info tab.");
//        myInfoTab = this.page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("MyInfo"));
        myInfoTab = this.page.locator("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[6]/a");
        log.info("After select my info tab. {}",myInfoTab);

    }
    public String getProfileName(){
        return page.title();
    }
    public Boolean isHomePage() {
        return actionsText.isVisible();
//        return actionsText.isHidden();

    }
    public Boolean isHomePage2() {
//        return actionsText.isVisible();
        return actionsText.isHidden();

    }

    public MyInfoPage navigateToMyInfoPage (){
//        page.waitForLoadState(LoadState.NETWORKIDLE);
//        myInfoTab.waitFor();
        log.info("this is myInfo Link: {}", myInfoTab);
        myInfoTab.click();
//        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7");
        log.info("after clicking on myInfo Tab");
        return new MyInfoPage(page);
    }
    public LoginPage logout(){
        page.click("xpath=//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']");
        page.click("xpath=//a[normalize-space()='Logout']");
        return new LoginPage(page);
    }

    // page actions/methods
    public String getHomePageTitle () {
        return page.title();
    }

    public String getHomePageUrl(){
        return page.url();
    }


}

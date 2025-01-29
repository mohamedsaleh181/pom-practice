package com.qa.orangehrm.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage {
    private final static Logger log = LoggerFactory.getLogger(LoginPage.class);

    private final Page page;
    // locators
    private final Locator password;
    private final Locator userName;
    private final Locator loginButton;
    // constructor
    public LoginPage(Page page){
        this.page = page;
        password = page.getByPlaceholder("Password");
//        password = page.getByPlaceholder("密碼");
        userName = page.getByPlaceholder("Username");
//        userName = page.getByPlaceholder("使用者名稱");
        loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
//        loginButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("登錄"));
        log.info("Here is HomePage constructor and UserName is: {}",userName);
        log.info("this is page: {}", this.page.url());
    }


    public String getLoginPageTitle () {
        String title = page.title();
        return title;
    }

    public String getLoginPageUrl () {
        String url = page.url();
        System.out.println("Page url is : " + url);
        return url;
    }

    public void enterUserName (String text) {
        userName.fill(text);
    }

    public void enterPassword (String text) {
        password.fill(text);
    }

    public HomePage clickonLoginButton () {
        loginButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForSelector("//p[normalize-space()='My Actions']"); // Adjust this selector to fit your app
        return new HomePage(page);
    }

    public HomePage doLogin(String username, String pass) {
        userName.fill(username);
        password.fill(pass);
        loginButton.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
//        page.waitForSelector("//p[normalize-space()='My Actions']"); // Adjust this selector to fit your app
        // Return HomePage object after successful login
        return new HomePage(page);
    }

}

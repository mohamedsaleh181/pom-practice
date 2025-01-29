package com.qa.orangehrm.utils;

import com.microsoft.playwright.Page;
import com.qa.orangehrm.pages.HomePage;
import com.qa.orangehrm.pages.LoginPage;

public class LoginUtil {
    private final LoginPage loginPage;

    public LoginUtil(Page page) {
        this.loginPage = new LoginPage(page);
    }

    public HomePage performLogin(String username, String password) {
        // Use the doLogin method from the LoginPage
        return loginPage.doLogin(username, password);
    }
}
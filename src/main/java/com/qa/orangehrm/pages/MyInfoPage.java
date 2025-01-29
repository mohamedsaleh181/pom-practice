package com.qa.orangehrm.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyInfoPage {
    private final static Logger log = LoggerFactory.getLogger(MyInfoPage.class);

    private final Page page;
    private final Locator contactPage;

    public MyInfoPage(Page page){
        this.page = page;
//        contactPage = this.page.getByText("Contact Details");
        contactPage = this.page.locator("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[2]/a");

    }

    public ContactDetailsPage navigateToContactDetailsPage(){

        contactPage.click();
        return new ContactDetailsPage(page);
    }
}

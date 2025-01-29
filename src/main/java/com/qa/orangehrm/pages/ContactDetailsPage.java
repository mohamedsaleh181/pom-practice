package com.qa.orangehrm.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ContactDetailsPage {
    private final static Logger log = LoggerFactory.getLogger(ContactDetailsPage.class);

    private final Page page;
    private final Locator pageHeader;
    private final Locator addAttachmentBtn;
    private final Locator saveBtn;
    private final Locator inputAttachment;

    public ContactDetailsPage (Page page){
        this.page = page;
        pageHeader = this.page.locator("//h6[normalize-space()='Contact Details']");
        addAttachmentBtn = this.page.locator("//button[normalize-space()='Add']");
        inputAttachment = this.page.locator("input[type='file']");
        saveBtn = this.page.locator("//div[@class='orangehrm-attachment']//button[@type='submit'][normalize-space()='Save']");
    }

    public Locator getPageHeader(){
        log.info("returning the page header.");
        return pageHeader;
    }

    public void addAttachment(String filePath){
        addAttachmentBtn.click();
        Path path = Paths.get(filePath);
        inputAttachment.setInputFiles(path);
        saveBtn.click();
    }

    public void getAttchments(){

    }

}

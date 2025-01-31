package com.qa.orangehrm.factory;

import com.microsoft.playwright.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class PlaywrightFactory {

    private static final Logger log = LoggerFactory.getLogger(PlaywrightFactory.class);

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties prop;



    public BrowserContext initBrowser (Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        log.info("Browser name is: {}.", browserName);
        playwright = Playwright.create();
        switch (browserName.toLowerCase()){
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(true));
                break;

            default:
                log.error("Please enter the right browser name......");
                break;
        }

        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("videos/"))  // Folder where videos will be saved
                .setRecordVideoSize(1280, 720));
        return browserContext;

    }

    public Properties init_prop () throws FileNotFoundException {
        try {
            FileInputStream ip = new FileInputStream("src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }

}

package com.qa.orangehrm.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TableUtil {
    private final static Logger log = LoggerFactory.getLogger(TableUtil.class);

    private final Page page;
    String tableSelector;

    public TableUtil(Page page) {
        this.page = page;
        // Select the table body where rows are present
        tableSelector = "#app > div.oxd-layout.orangehrm-upgrade-layout > div.oxd-layout-container > div.oxd-layout-context > div > div > div > div.orangehrm-edit-employee-content > div.orangehrm-attachment > div.orangehrm-container > div";
        // Get all the rows in the table body
        List<Locator> rows = page.locator(tableSelector + "< div").all();
        // Loop through each row and validate its contents
        for(int rowIndex = 0; rowIndex < rows.size(); rowIndex++){
            Locator row = rows.get(rowIndex);

            String fileName = row.locator("div:nth-child(2)").textContent().trim();
            String description = row.locator("div:nth-child(3)").textContent().trim();
            String size = row.locator("div:nth-child(4)").textContent().trim();
            String type = row.locator("div:nth-child(5)").textContent().trim();
            String dateAdded = row.locator("div:nth-child(6)").textContent().trim();
            String addedBy = row.locator("div:nth-child(7)").textContent().trim();
            log.info("Row {} : File Name = {}", rowIndex+1,fileName);
    //        log.info("Row " + (rowIndex + 1) + ": Description = " + description);
    //        log.info("Row " + (rowIndex + 1) + ": Size = " + size);
    //        log.info("Row " + (rowIndex + 1) + ": Type = " + type);
    //        log.info("Row " + (rowIndex + 1) + ": Date Added = " + dateAdded);
    //        log.info("Row " + (rowIndex + 1) + ": Added By = " + addedBy);


        }

    }}

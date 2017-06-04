package org.oscar.gradle.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * Created by Oscar on 4/6/2017.
 */
public class InboxPage extends PageBase {
    @FindBy(css = "[href='https://mail.google.com/mail/u/0/#inbox']")
    WebElement inbox;
    /**
     * Constructor
     *
     * @param driver
     */
    public InboxPage(WebDriver driver) {
        super(driver);
        this.current_page_url = "/mail/u/0/#inbox";
    }

    @Override
    protected void load() {
        driver.get(BASE_URL.concat(current_page_url));
    }

    @Override
    public void isLoaded() throws Error {
        Assert.assertTrue(botStyle.isElementDisplayed(inbox),"Page was not loaded as expected");
    }
}

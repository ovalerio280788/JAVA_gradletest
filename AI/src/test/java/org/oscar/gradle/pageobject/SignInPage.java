package org.oscar.gradle.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * Created by Oscar on 4/6/2017.
 */
public class SignInPage extends PageBase {

    @FindBy(id = "identifierId")
    WebElement identifierId;

    @FindBy(xpath = "//*[text()='Next' or text()='Siguiente']")
    WebElement identifierNext;

    @FindBy(name = "password")
    WebElement password;

    /**
     * Constructor
     *
     * @param driver
     */
    public SignInPage(WebDriver driver) {
        super(driver);
        this.current_page_url = "https://accounts.google.com/signin";
    }

    public SignInPage fillIdField(String value){
        botStyle.type(identifierId, value);
        return this;
    }

    public SignInPage fillPassword(String value){
        botStyle.type(password, value);
        return this;
    }

    public SignInPage clickNext(){
        botStyle.click(identifierNext, 10);
        return this;
    }

    public InboxPage login(){
        botStyle.click(identifierNext, 10);
        botStyle.click(options, 10);
        botStyle.click(emailOption, 10);
        return new InboxPage(driver);
    }

    @Override
    protected void load() {
        driver.get(this.current_page_url);
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(botStyle.isElementDisplayed(identifierId),"Page was not loaded as expected");
    }
}

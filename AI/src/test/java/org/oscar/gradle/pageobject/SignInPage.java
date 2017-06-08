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
     * @param driver The webdriver instance
     */
    public SignInPage(WebDriver driver) {
        super(driver);
        this.current_page_url = "https://accounts.google.com/signin";
    }

    /**
     * This method fill the email/user name field
     * @param value The username / email
     * @return An instance of this class
     */
    public SignInPage fillIdField(String value){
        botStyle.type(identifierId, value);
        return this;
    }

    /**
     * This method fill the password field
     * @param value The password value
     * @return An instance of this class
     */
    public SignInPage fillPassword(String value){
        botStyle.type(password, value);
        return this;
    }

    /**
     * This method clicks on the next button
     * @return An instance of this class
     */
    public SignInPage clickNext(){
        botStyle.click(identifierNext, 10);
        return this;
    }

    /**
     * This method clicks on the next button and do the login procces, tha means that have to retunr an .
     * instance of the following page
     * @return A new Inbox page instance
     */
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

package org.oscar.gradle.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.oscar.gradle.botstyle.BotStyle;
import org.oscar.gradle.constant.Constant;

/**
 * Created by Oscar on 4/6/2017.
 */
public abstract class PageBase extends LoadableComponent {

    protected String BASE_URL = Constant.BASE_URL;
    protected String current_page_url = "";
    protected WebDriver driver;
    protected BotStyle botStyle;

    @FindBy(css = "[href='https://www.google.co.cr/intl/es/options/']")
    WebElement options;

    @FindBy(css = "[href='https://mail.google.com/mail/']")
    WebElement emailOption;

    /**
     * Constructor
     * @param driver The webdriver instance
     */
    public PageBase(WebDriver driver){
        this.driver = driver;
        botStyle = new BotStyle(driver);
        PageFactory.initElements(driver, this);
    }
}

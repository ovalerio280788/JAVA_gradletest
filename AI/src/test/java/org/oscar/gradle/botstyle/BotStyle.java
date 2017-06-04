package org.oscar.gradle.botstyle;

import com.google.common.base.Function;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Oscar on 4/6/2017.
 */
public class BotStyle {

    private WebDriver driver;

    public BotStyle(WebDriver driver){
        this.driver = driver;
    }

    public WebElement waitForElementPresent(int timeoutInSeconds, final WebElement element){
        Wait<WebDriver> wait = new WebDriverWait(driver, timeoutInSeconds);
        WebElement we= wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return element;
            }
        });
        return we;
    }

    public void type(WebElement element, String text){
        waitForElementPresent(60, element);
        element.clear();
        element.sendKeys(text);
    }

    public void click(WebElement element, int timeOuts){
        waitForElementPresent(timeOuts, element);
        element.click();
    }

    public boolean isElementDisplayed(WebElement element){
        try {
            element.isDisplayed();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

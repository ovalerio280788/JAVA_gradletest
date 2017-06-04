package org.oscar.gradle.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

/**
 * created by oscar on 4/6/2017.
 */
public class SeleniumBase {
    private WebDriver driver;

    /**
     * This method opens the browser (IE, FF, Chrome) given by parameter
     * @param browserName The browser to open
     * @return driver - the opened driver
     */
    public WebDriver setup(String browserName){
        switch (browserName){
            case Browser.CHROME:
                initChrome();
                break;
            case Browser.IE:
                initIE();
                break;
            case  Browser.FIREFOX:
                initFireFox();
                break;
            default:
                System.out.println("Browser not supported");
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public void tearDown(){
        driver.quit();
    }

    private void initChrome(){
        this.driver = new ChromeDriver();
    }

    private void initIE(){
        this.driver = new InternetExplorerDriver();
    }

    private void initFireFox(){
        this.driver = new FirefoxDriver();
    }
}

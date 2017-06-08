package org.oscar.gradle.testsuite;

import org.openqa.selenium.WebDriver;
import org.oscar.gradle.selenium.SeleniumBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

/**
 * Created by Oscar on 4/6/2017.
 */
public class TestCaseBase extends SeleniumBase {
    protected static WebDriver driver;

    /**
     * This method call another method to start the specified driver before each test case run
     * @param browser The webdriver to use (FF, Chrome, IE)
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(String browser){
        driver = setup(browser);
    }

    /**
     * This method calls another method to close the driver after each test case run
     */
    @AfterMethod(alwaysRun = true)
    protected void quitBrowser(){
        tearDown();
    }
}

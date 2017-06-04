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

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(String browser){
        driver = setup(browser);
    }

    @AfterMethod(alwaysRun = true)
    protected void quitBrowser(){
        tearDown();
    }
}

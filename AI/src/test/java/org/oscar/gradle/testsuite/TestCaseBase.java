package org.oscar.gradle.testsuite;

import org.oscar.gradle.selenium.SeleniumBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

/**
 * Created by Oscar on 4/6/2017.
 */
public class TestCaseBase extends SeleniumBase {
    private String testRailBaseUrl = "";
    private String trProject = "";
    private String trTestPlanName = "";
    private String trUser = "";
    private String trPassword = "";
    private String trCaseId = "";

    /**
     * This method call another method to start the specified driver before each test case run
     * @param browser The webdriver to use (FF, Chrome, IE)
     */
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser", "tr_base_url", "tr_project", "tr_test_plan_name", "tr_user", "tr_password"})
    public void setUp(String browser, String trBaseUrl, String trProject, String trTestPlanName, String trUser,
                      String trPassword){
        setup(browser);
        this.testRailBaseUrl = trBaseUrl;
        this.trProject = trProject;
        this.trTestPlanName = trTestPlanName;
        this.trUser = trUser;
        this.trPassword = trPassword;
    }

    /**
     * This method calls another method to close the driver after each test case run
     */
    @AfterMethod(alwaysRun = true)
    protected void quitBrowser(){
        tearDown();
    }

    public String getTestRailBaseUrl() {
        return testRailBaseUrl;
    }

    public void setTestRailBaseUrl(String testRailBaseUrl) {
        this.testRailBaseUrl = testRailBaseUrl;
    }

    public String getTrProject() {
        return trProject;
    }

    public void setTrProject(String trProject) {
        this.trProject = trProject;
    }

    public String getTrTestPlanName() {
        return trTestPlanName;
    }

    public void setTrTestPlanName(String trTestPlanName) {
        this.trTestPlanName = trTestPlanName;
    }

    public String getTrUser() {
        return trUser;
    }

    public void setTrUser(String trUser) {
        this.trUser = trUser;
    }

    public String getTrPassword() {
        return trPassword;
    }

    public void setTrPassword(String trPassword) {
        this.trPassword = trPassword;
    }

    public String getTrCaseId() {
        return trCaseId;
    }

    public void setTrCaseId(String trCaseId) {
        this.trCaseId = trCaseId;
    }
}

package org.oscar.gradle.testsuite;

import org.oscar.gradle.pageobject.SignInPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Oscar on 14/6/2017.
 */
public class TestRailSuite extends TestCaseBase {

    @Test(groups = {"testrail"})
    @Parameters({"testcase02_id"})
    public void testRailTest02(String caseId){
        setTrCaseId(caseId);
        Assert.assertTrue(true);
    }

    @Test(groups = {"testrail"})
    @Parameters({"testcase03_id"})
    public void testRailTest03(String caseId){
        setTrCaseId(caseId);
        new SignInPage(getDriver()).get();
        Assert.assertTrue(false);
    }

    @Test(groups = {"testrail"})
    @Parameters({"testcase04_id"})
    public void testRailTest04(String caseId){
        setTrCaseId(caseId);
        new SignInPage(getDriver()).get();
        Assert.assertTrue(false);
    }

    @Test(groups = {"testrail"})
    @Parameters({"testcase05_id"})
    public void testRailTest05(String caseId){
        setTrCaseId(caseId);
        new SignInPage(getDriver()).get();
        Assert.assertTrue(true);
    }
}

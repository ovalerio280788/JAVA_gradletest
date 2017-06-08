package org.oscar.gradle.testsuite;

import org.oscar.gradle.pageobject.InboxPage;
import org.oscar.gradle.pageobject.SignInPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Oscar on 4/6/2017.
 */
public class SignInSuite extends TestCaseBase{

    @Test(groups = {"full_regression","login"})
    @Parameters({"username", "password"})
    public void ValidLoginTest(String userName, String passWord) throws InterruptedException {
        SignInPage signInPage = (SignInPage) new SignInPage(driver).get();
        signInPage.fillIdField(userName);
        Thread.sleep(1000);
        signInPage.clickNext();
        Thread.sleep(1000);
        signInPage.fillPassword(passWord);
        Thread.sleep(1000);

        InboxPage inboxPage = signInPage.login();
        inboxPage.isLoaded();
    }
}

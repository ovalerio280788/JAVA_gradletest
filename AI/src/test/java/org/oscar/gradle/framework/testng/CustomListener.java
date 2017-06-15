package org.oscar.gradle.framework.testng;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.oscar.gradle.framework.testrail.APIException;
import org.oscar.gradle.framework.testrail.TestRailLib;
import org.oscar.gradle.testsuite.TestCaseBase;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

/**
 * Created by Oscar on 7/6/2017.
 */
public class CustomListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("\t|-- We are in the onTestStart method");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("\t|-- We are in the onTestSuccess method");
        printExecutionSummary(result);

        try {
            updateTrTestStatus(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("\t|-- We are in the onTestFailure method");
        printExecutionSummary(result);

        try {
            updateTrTestStatus(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("\t|-- We are in the onTestSkipped method");
        printExecutionSummary(result);

        try {
            updateTrTestStatus(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("\t|-- We are in the onTestFailedButWithinSuccessPercentage method");
        printExecutionSummary(result);

        try {
            updateTrTestStatus(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("\t|-- We are in the onStart method");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\t|-- We are in the onFinish method");
    }

    /**
     * This method prints a execution summary information in console
     * @param result The result object that contains all the information we need
     */
    private void printExecutionSummary(ITestResult result){
        WebDriver driver = ((TestCaseBase)(result.getInstance())).getDriver();

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n*******************************************************************************");
        System.out.println("*\t\t\tSUMMARY");
        System.out.println("*******************************************************************************");
        System.out.println("\t|-- Test Class:\t".concat(result.getInstanceName()));
        System.out.println("\t|-- Test Name:\t".concat(result.getName()));
        System.out.println("\t|-- Execution time:\t".concat(
                String.valueOf(result.getEndMillis() - result.getStartMillis())));
        System.out.println("\t|-- Status:\t".concat(getStatusLabel(result.getStatus())));
        System.out.println("*******************************************************************************\n\n\n");
    }

    /**
     * This method convert the result id of a test case to a result label value
     * @param statusId The id to convert from id to label
     * @return <p>The label as a string value of the status of the test case</p>
     */
    private String getStatusLabel(int statusId){
        switch (statusId){
            case ITestResult.SUCCESS:
                return "PASSED";
            case ITestResult.FAILURE:
                return "FAILED";
            case ITestResult.SKIP:
                return "SKIPPED";
            default:
                return "Unable to get the status label for id: ".concat(String.valueOf(statusId));
        }
    }

    /**
     * This method call TR api in order to update the test status
     * @param result The result object
     * @throws IOException
     * @throws APIException
     */
    private void updateTrTestStatus(ITestResult result) throws IOException, APIException {
        TestRailLib tr = new TestRailLib(
                ((TestCaseBase)(result.getInstance())).getTestRailBaseUrl(),
                ((TestCaseBase)(result.getInstance())).getTrUser(),
                ((TestCaseBase)(result.getInstance())).getTrPassword()
        );

        tr.updateTestCaseStatus(
                ((TestCaseBase)(result.getInstance())).getTrProject(),
                ((TestCaseBase)(result.getInstance())).getTrTestPlanName(),
                ((TestCaseBase)(result.getInstance())).getTrCaseId(),
                getStatusLabel(result.getStatus()),
                "This is a comment"
        );
    }
}

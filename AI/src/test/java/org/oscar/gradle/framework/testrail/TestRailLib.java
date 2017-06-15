package org.oscar.gradle.framework.testrail;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oscar on 14/6/2017.
 */
public class TestRailLib
{
    private APIClient client = null;

    /**
     * This constructor will create the instance of the TestRail API
     * @param baseUrl TR base URL
     * @param trUser TR user name
     * @param trPassword TR user password
     */
    public TestRailLib(String baseUrl, String trUser, String trPassword){
        client = new APIClient(baseUrl);
        client.setUser(trUser);
        client.setPassword(trPassword);
    }

    /**
     * This method gets the project id base on its name
     * @param projectName TR project name
     * @return TR project id
     */
    public String getProjectId(String projectName){
        String projectId = "";

        try {
            JSONArray jsonProjects = (JSONArray)client.sendGet("get_projects");

            for(Object project: jsonProjects){
                if (String.valueOf(((JSONObject) project).get("name")).equals(projectName)){
                    projectId = String.valueOf(((JSONObject) project).get("id"));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return projectId;
    }

    /**
     * This method get the test id based on its case id
     * @param testPlan The test Plan name
     * @param caseId The case id value
     * @return The Test execution id
     * @throws IOException
     * @throws APIException
     */
    public String getTestIdValue(String testPlan, String caseId) throws IOException, APIException {
        String testId = "";
        boolean found = false;

        // get the suites
        JSONObject jsonPlans = (JSONObject)client.sendGet(String.format("get_plan/%s", testPlan));
        JSONArray entries = (JSONArray) jsonPlans.get("entries");

        for (Object entry : entries){
            JSONArray runs = (JSONArray) ((JSONObject) entry).get("runs");
            for (Object run : runs){
                String currentRunId = String.valueOf(((JSONObject) run).get("id"));

                //check if the testcase belongs to this current run
                JSONArray testCasesForThisRun = (JSONArray) client.sendGet(String.format("get_tests/%s", currentRunId));

                for (Object test : testCasesForThisRun){
                    // if the case id match, then return the test id
                    if (String.valueOf(((JSONObject) test).get("case_id")).equals(caseId)){
                        testId = String.valueOf(((JSONObject) test).get("id"));
                        found = true;
                        break;
                    }
                }
                if (found)
                    break;
            }
            if (found)
                break;
        }
        return testId;
    }

    /**
     * <p>This method get the test plan id </p>
     * @param projectId Id of the project that contains the test plan
     * @return Test Plan ID
     * @throws IOException
     */
    public String getTestPlanId(String projectId, String testPlanName) throws IOException, APIException {
        String testPlanId = "";
        // get list of test plans
        JSONArray testPlans = (JSONArray)client.sendGet(String.format("get_plans/%s", projectId));

        for(Object testPlan: testPlans){
            if (String.valueOf(((JSONObject) testPlan).get("name")).equals(testPlanName)){
                testPlanId = String.valueOf(((JSONObject) testPlan).get("id"));
                break;
            }
        }
        return testPlanId;
    }

    /**
     * This method gets the status value of a test status label
     * @param statusLabel The status label to translate
     * @return The status id
     */
    private int getStatusId(String statusLabel){
        switch (statusLabel){
            case "PASSED":
                return 1;
            case "FAILED":
                return 5;
            case "BLOCKED":
                return 2;
            case "UNTESTED":
                return 3;
            default:
                return 0;
        }
    }

    /**
     * This method update the status of a testcase after the execution
     * @param trProject TR Project
     * @param testPlanName TR Test Plan
     * @param caseId Case id
     * @param status New status for the testcase defined if the test passed/failed
     * @param comment A comment
     * @return True/False if the update is done successfully or not
     * @throws IOException Exceptions
     * @throws APIException Exceptions
     */
    public boolean updateTestCaseStatus(String trProject, String testPlanName, String caseId,
                                        String status, String comment) throws IOException, APIException {
        boolean updated = false;
        Map data = new HashMap();
        data.put("status_id", getStatusId(status));
        data.put("comment", comment);
        data.put("custom_tested_by_automation", true);
        String projectId = getProjectId(trProject);
        String testPlanId = getTestPlanId(projectId, testPlanName);
        String testId = getTestIdValue(testPlanId, caseId.replace("C",""));

        if ( ! testId.isEmpty()){
            JSONObject json = (JSONObject) client.sendPost(String.format("add_result/%s", testId), data);

            if (String.valueOf(json.get("status_id")).equals(String.valueOf(status)) &&
                    String.valueOf(json.get("test_id")).equals(testId)
                    && String.valueOf(json.get("custom_tested_by_automation")).equals("true")){
                System.out.println(String.format("The test case (%s / %s) was updated on testrail succesfully", caseId, testId));
                updated = true;
            }
        }
        return updated;
    }
}

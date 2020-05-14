package azure.controller;

import azure.model.testresult.Results;
import azure.model.testresult.ResultTestCase;
import cucumber.api.Scenario;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

@Log4j2
public class RunTestController extends GenericController  {

    public void runTestCase(Scenario scenario) {

        CucumberController tagsCucumber = new CucumberController(scenario);
        String url = String.format("%s_apis/testplan/Plans/%s/Suites/%s/TestPoint?api-version=5.1-Preview&includePointDetails=true&returnIdentityRef=true"
                , getBaseUrl(), tagsCucumber.getPlanId(), tagsCucumber.getSuiteId());

        RequestSpecification httpRequest = given();
        httpRequest.contentType(ContentType.JSON);
        httpRequest.header("Authorization", "Basic " + LoginController.getToken(azureConfig.personalToken()));

        List<ResultTestCase> resultTestCases = Collections.singletonList(
                new ResultTestCase(getPointIdFromTestCase(scenario, tagsCucumber.getTestId()),
                        new Results(CucumberController.getStatus(scenario))));

        httpRequest.body(resultTestCases);

        Response response = httpRequest.patch(url);
        AttachmentController attachmentController = new AttachmentController();
        attachmentController.addAttachment(response, scenario);

    }

    private Integer getPointIdFromTestCase(Scenario scenario, String testId){
        CucumberController tagsCucumber = new CucumberController(scenario);
        RequestSpecification httpRequest = given();
        httpRequest.contentType(ContentType.JSON);
        httpRequest.header("Authorization", "Basic " + LoginController.getToken(azureConfig.personalToken()));
        String url = String.format("%s_apis/testplan/Plans/%s/Suites/%s/TestPoint?api-version=5.1-Preview&testCaseId=%s&includePointDetails=true&returnIdentityRef=true"
                ,getBaseUrl(),tagsCucumber.getPlanId(), tagsCucumber.getSuiteId(),testId);
        Response response = httpRequest.get(url);
        return response.jsonPath().get("value.id[0]");
    }

}

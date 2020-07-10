package core.azure.controller;

import core.azure.model.testresult.Results;
import core.azure.model.testresult.ResultTestCase;
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

        LoginController loginController = new LoginController();
        loginController.generateToken();

        if( !loginController.getToken().isEmpty()) {
            try {
                RequestSpecification httpRequest = given();
                httpRequest.contentType(ContentType.JSON);
                httpRequest.header("Authorization", "Basic " + loginController.getToken());

                List<ResultTestCase> resultTestCases = Collections.singletonList(
                        new ResultTestCase(getPointIdFromTestCase(scenario, tagsCucumber.getTestId(), loginController.getToken()),
                                new Results(CucumberController.getStatus(scenario))));

                httpRequest.body(resultTestCases);

                Response response = httpRequest.patch(url);

                if (response.getStatusCode() == 200) {
                    log.info("Execução registrada com sucesso");
                    AttachmentController attachmentController = new AttachmentController();
                    attachmentController.addAttachment(response, scenario, loginController.getToken());
                } else {
                    log.error("Erro ao tentar registrar execução do teste", response.getBody().print());
                }
            }catch (Exception e){
                log.error("Erro ao tentar registrar execução do teste", e.getMessage());
            }
        }else {
            log.warn("Personal access token não informado!");
        }


    }

    private Integer getPointIdFromTestCase(Scenario scenario, String testId, String token){
        Response response;

        CucumberController tagsCucumber = new CucumberController(scenario);
        RequestSpecification httpRequest = given();
        httpRequest.contentType(ContentType.JSON);
        httpRequest.header("Authorization", "Basic " + token);
        String url = String.format("%s_apis/testplan/Plans/%s/Suites/%s/TestPoint?api-version=5.1-Preview&testCaseId=%s&includePointDetails=true&returnIdentityRef=true"
                , getBaseUrl(), tagsCucumber.getPlanId(), tagsCucumber.getSuiteId(), testId);
        response = httpRequest.get(url);
        if(response.getStatusCode() == 200){
            return response.jsonPath().get("value.id[0]");
        }else {
            log.error("Erro ao tentar recuperar o Ponit id do caso de teste! Response code: " +response.getStatusCode());
            return null;
        }
    }

}

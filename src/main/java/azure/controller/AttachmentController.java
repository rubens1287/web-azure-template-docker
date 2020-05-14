package azure.controller;

import azure.model.attachment.Attachment;
import cucumber.api.Scenario;
import driver.DriverManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static io.restassured.RestAssured.given;

public class AttachmentController extends GenericController{


    public void addAttachment(Response response, Scenario scenario){

        String url = String.format("%s_apis/test/Runs/%s/Results/100000/attachments?api-version=5.1-preview"
                ,getBaseUrl(),response.jsonPath().get("value.results.lastTestRunId[0]"));

        RequestSpecification httpRequest = given();
        httpRequest.contentType(ContentType.JSON);
        httpRequest.header("Authorization", "Basic " +  LoginController.getToken(azureConfig.personalToken()));

        Attachment attachment = Attachment.builder()
                .stream(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64))
                .fileName("Screeshot.png")
                .comment("Screenshot  anexada via teste automatizado")
                .attachmentType("GeneralAttachment").build();

        httpRequest.body(attachment);
        httpRequest.post(url);
    }


}

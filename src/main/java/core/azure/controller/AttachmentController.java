package core.azure.controller;

import core.azure.model.attachment.Attachment;
import cucumber.api.Scenario;
import core.driver.DriverManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;

import java.util.Iterator;

import static io.restassured.RestAssured.given;

@Log4j2
public class AttachmentController extends GenericController{


    public void addAttachment(Response response, Scenario scenario){

        log.info("Preparando para enviar as evidências para o Test Management do Azure Devosp");
        String url = String.format("%s_apis/test/Runs/%s/Results/100000/attachments?api-version=5.1-preview"
                ,getBaseUrl(),response.jsonPath().get("value.results.lastTestRunId[0]"));

        RequestSpecification httpRequest = given();
        httpRequest.contentType(ContentType.JSON);
        httpRequest.header("Authorization", "Basic " +  LoginController.getToken());

        for (Iterator<Attachment> iterator = DriverManager.attachments.iterator(); iterator.hasNext();) {
            Attachment attachment = iterator.next();
            httpRequest.body(attachment);
            response = httpRequest.post(url);
        }

        if(response.getStatusCode()==200){
            log.info("Teste atualizado com as evidências");
        }else{
            log.error("Erro ao tentar atualizar o registro do teste realizado", response.getBody().print());
        }
    }
}


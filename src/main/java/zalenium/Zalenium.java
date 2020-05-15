package zalenium;

import azure.controller.CucumberController;
import azure.controller.LoginController;
import azure.model.testresult.ResultTestCase;
import azure.model.testresult.Results;
import driver.DriverManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;
import support.Verifications;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static javafx.application.Platform.exit;

@Log4j2
public class Zalenium extends DriverManager {


    public static void waitToBeReady(String url){

        RequestSpecification httpRequest = given();
        int timeout = 0;
        while (httpRequest.get(url+"/status").getStatusCode() != 200 && timeout <= configuration.timeout()) {
            Verifications.wait(1);
            if (timeout == configuration.timeout()) {
                log.error("Execedeu o time out para disponibilizar o servidor do zalenium");
                exit();
            }
            timeout++;
        }
    }
}

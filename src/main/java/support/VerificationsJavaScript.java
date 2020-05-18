package support;

import core.driver.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;


@Log4j2
public class VerificationsJavaScript extends DriverManager {


    public static boolean verifyText(String id, Object expectedText){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        int timeout = 0;
        while (!(js.executeScript("return document.getElementById('"+id+"').value").toString().trim()
                .equals(expectedText)) && (timeout <= configuration.timeout())) {
            Verifications.wait(1);
            if (timeout == configuration.timeout()) {
                log.error(String.format("Elemento via locator id: %s nao é igual attachment valor esperado: %s "),id,expectedText);
                return false;
            }
            timeout++;
        }
        return true;

    }
}

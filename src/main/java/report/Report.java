package report;

import driver.DriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Log4j2
public class Report extends DriverManager {


    @Attachment(value = "Page Screenshot", type = "image/png")
    public static byte[] takeScreenShot() {
        log.info("Tirando print screen da tela");
        String target = System.getProperty("report").toUpperCase();
        switch (target) {
            case "CUCUMBER":
                getScenario().get().embed(((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES),"image/png");
            case "ALLURE":
                return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES);
            default:
                throw new IllegalStateException("Unexpected value: " + target);
        }
    }

    public static void appendInfo(String info) {
        log.info("Anexa informações ao report");
        String target = System.getProperty("report").toUpperCase();
        switch (target) {
            case "CUCUMBER":
                getScenario().get().write(info);
                break;
            case "ALLURE":
                Allure.addDescription(info);
                break;
        }
    }

}
